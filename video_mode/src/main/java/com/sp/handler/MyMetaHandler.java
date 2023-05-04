package com.sp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description: mybatis-plush 设置时间配置
 * @Author: lcf
 * @Date: 2023/3/4 11:06
 */
@Component
public class MyMetaHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("commentTime", new Date(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {

  }
}
