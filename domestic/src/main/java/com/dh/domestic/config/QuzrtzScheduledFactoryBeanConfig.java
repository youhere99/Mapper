package com.dh.domestic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 
 * Title.
 * <p>
 * Description.单体情况下
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午2:51:15
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
@Configuration
public class QuzrtzScheduledFactoryBeanConfig {

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() {
    return new SchedulerFactoryBean();
  }
}
