package com.sp.pojo.vo;

/**
 * @Description: 返回状态码
 * @Author: sp
 * @Date: 2023/3/4 9:42
 */
public enum ResultCode {
  SUCCESS(20000),
  ERROR(20001);
  private final int code;

  ResultCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
