package com.dh.domestic.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * Title.
 * <p>
 * Description.定时表
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午2:32:35
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "QUARTZ")
public class Quartz implements Serializable {
  /**
   * 任务id
   */
  @Id
  @Column(name = "JOB_ID")
  private String jobId;
  /**
   * 任务名称
   */
  @NotBlank(message = "任务名称不能为空")
  @Column(name = "JOB_NAME")
  private String jobName;
  /**
   * 任务分组
   */
  @Column(name = "JOB_GROUP")
  private String jobGroup;
  /**
   * 任务状态 0禁用 1启用 2删除
   */
  @Column(name = "JOB_STATUS")
  private String jobStatus;
  /**
   * 任务运行时间表达式
   */
  @NotBlank(message = "任务运行时间表达式不能为空")
  @Column(name = "CRON_EXPRESSION")
  private String cronExpression;
  /**
   * 任务创建时间
   */
  @Column(name = "CREATE_TIME")
  private Date createTime;
  /**
   * 任务更新时间
   */
  @Column(name = "UPDATE_TIME")
  private Date updateTime;
  /**
   * 任务描述
   */
  @Column(name = "DESCS")
  private String descs;
}
