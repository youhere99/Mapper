package com.dh.domestic.config;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import com.dh.domestic.domain.Quartz;
import com.dh.domestic.service.QuartzService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Title.
 * <p>
 * Description.我们预先定义的希望在未来时间能被调度程序执行的任务类
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午4:34:08
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
@Slf4j
public class QuartzJobFactory implements Job {

  @Autowired
  private QuartzService quartzService;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    Quartz quartz = (Quartz) dataMap.get("Quartz");
    log.info("定时任务 {} 成功运行", quartz.getJobName());
    quartz.setUpdateTime(new Date());
    // quartzService.updateByPrimaryKey(quartz);
    log.info("更新定时任务 {}最后 成功运行 的时间", quartz.getJobName());
    // 根据name 与 group组成的唯一标识来判别该执行何种操作……
  }
}