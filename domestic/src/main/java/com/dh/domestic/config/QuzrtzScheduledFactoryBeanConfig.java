package com.dh.domestic.config;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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

  @Autowired
  private DataSource dataSource;

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() throws IOException {

    SchedulerFactoryBean schedulerFactoryBean= new SchedulerFactoryBean();
    schedulerFactoryBean.setDataSource(dataSource);
    schedulerFactoryBean.setOverwriteExistingJobs(true);
    schedulerFactoryBean.setStartupDelay(3);
    schedulerFactoryBean.setAutoStartup(true);
    schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
    schedulerFactoryBean.setSchedulerName("ClusterScheduler");
    schedulerFactoryBean.setQuartzProperties(quartzProperties());
    return schedulerFactoryBean;
  }

  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }
}
