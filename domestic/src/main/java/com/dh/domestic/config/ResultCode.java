package com.dh.domestic.config;

/**
 * 
 * Title.状态码
 * <p>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年5月22日 下午2:37:36
 * <p>
 * Company:
 * <p>
 * 
 * @author zhaomingxing
 * @version 1.8
 */
public class ResultCode {

  public final static int SUCCESS = 1;// 成功
  // 通用错误以9开头
  public final static int ERROR = 9999;// 未知错误
  public final static int APPLICATION_ERROR = 9000;// 应用级错误
  public final static int VALIDATE_ERROR = 9001;// 参数验证错误
  public final static int SERVICE_ERROR = 9002;// 业务逻辑验证错误
  public final static int CACHE_ERROR = 9003;// 缓存访问错误
  public final static int DAO_ERROR = 9004;// 数据访问错误
}
