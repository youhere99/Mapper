package com.dh.domestic.controller;

import java.util.Date;
import org.hibernate.validator.constraints.NotBlank;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dh.domestic.config.QuartzJobFactory;
import com.dh.domestic.config.Result;
import com.dh.domestic.config.ResultCode;
import com.dh.domestic.domain.Quartz;
import com.dh.domestic.service.QuartzService;
import com.dh.domestic.utils.UUIDUtils;

/**
 * 
 * Title.
 * <p>
 * Description. 定时器相关操作
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午2:30:14
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
@RestController
@RequestMapping(value = "/quartz")
public class QuartzController {
  /**
   * 默认分组
   */
  private static String QUARTZ_GROUP = "QUARTZ_GROUP";

  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;

  @Autowired
  private QuartzService quartzService;


  /**
   * 
   * 功能：创建定时任务
   * 
   * @param quartz
   * @param bindingResult
   * @return 2018年5月22日下午4:01:08 zhaomingxing
   * @throws SchedulerException
   */
  @PostMapping()
  public Result<Quartz> add(@RequestBody @Validated Quartz quartz, BindingResult bindingResult)
      throws SchedulerException {
    if (bindingResult.hasErrors()) {
      for (FieldError fieldError : bindingResult.getFieldErrors()) {
        return Result.error(ResultCode.VALIDATE_ERROR, fieldError.getDefaultMessage());
      }
    }

    quartz.setJobId(UUIDUtils.getUUID());
    quartz.setCreateTime(new Date());
    quartzService.insertSelective(quartz);

    // 创建任务
    JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
        .withIdentity(quartz.getJobId(), QUARTZ_GROUP).build();

    jobDetail.getJobDataMap().put("Quartz", quartz);

    // 表达式调度构建器
    CronScheduleBuilder cronScheduleBuilder =
        CronScheduleBuilder.cronSchedule(quartz.getCronExpression());

    // 获取触发器标识
    TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobId(), QUARTZ_GROUP);
    // 获取触发器trigger
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
    // 按新的cronExpression表达式构建一个新的trigger
    trigger = TriggerBuilder.newTrigger().withIdentity(quartz.getJobId(), QUARTZ_GROUP)
        .withSchedule(cronScheduleBuilder).build();

    scheduler.scheduleJob(jobDetail, trigger);

    return Result.success();

  }

  /**
   * 
   * 功能：更新定时任务
   * 
   * @param quartz
   * @return 2018年5月22日下午5:18:55 zhaomingxing
   * @throws SchedulerException
   */
  @PutMapping(value = "/{id}")
  public Result<Quartz> update(@PathVariable String id,
      @RequestBody @NotBlank(message = "任务运行时间表达式不能为空") String cronExpression)
      throws SchedulerException {
    // 获取触发器标识
    TriggerKey triggerKey = TriggerKey.triggerKey(id, QUARTZ_GROUP);
    // 获取触发器trigger
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
    // 表达式调度构建器
    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
    // 按新的cronExpression表达式重新构建trigger
    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder)
        .build();

    // 按新的trigger重新设置job执行
    scheduler.rescheduleJob(triggerKey, trigger);
    Quartz quartz =
        Quartz.builder().jobId(id).cronExpression(cronExpression).updateTime(new Date()).build();
    // 更新数据库中的任务
    quartzService.updateSelective(quartz);
    return Result.success();
  }

  /**
   * 
   * 功能：更新任务状态
   * 
   * @param id
   * @return 2018年5月22日下午5:35:27 zhaomingxing
   * @throws SchedulerException
   */
  @PatchMapping(value = "/{id}")
  public Result<Quartz> pauseQuartz(@PathVariable String id,
      @NotBlank(message = "状态不能为空") String status) throws SchedulerException {

    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    JobKey jobKey = JobKey.jobKey(id, QUARTZ_GROUP);
    Quartz quartz = Quartz.builder().jobId(id).updateTime(new Date()).build();
    if ("0".equals(status)) {
      scheduler.pauseJob(jobKey);
      quartz.setJobStatus("0");
    } else if ("1".equals(status)) {
      scheduler.resumeJob(jobKey);
      quartz.setJobStatus("1");
    } else if ("2".equals(status)) {
      scheduler.deleteJob(jobKey);
      quartz.setJobStatus("2");
    }
    // 更新数据库中的任务
    quartzService.updateSelective(quartz);
    return Result.success();
  }

}
