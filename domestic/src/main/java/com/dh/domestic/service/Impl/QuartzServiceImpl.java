package com.dh.domestic.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dh.domestic.base.BaseServiceImpl;
import com.dh.domestic.dao.QuartzDao;
import com.dh.domestic.domain.Quartz;
import com.dh.domestic.service.QuartzService;

/**
 * 
 * Title. 自定义实现类
 * <p>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年5月11日 下午2:04:41
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
@Service
public class QuartzServiceImpl extends BaseServiceImpl<Quartz> implements QuartzService {
  @Autowired
  private QuartzDao quartzDao;



}
