package com.sp.pojo.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 统一返回数据实体类
 * @Author: sp
 * @Date: 2023/3/4 9:49
 */
@Data
public class ResultVO {
  // 是否成功
  private Boolean success;
  //返回状态码
  private Integer code;
  //返回信息
  private String message;
  //返回数据
  private Map<String, Object> data = new HashMap<>();

  //私有化构造方法
  private ResultVO(){}

  //成功的静态方法
  public static ResultVO ok(){
    ResultVO resultVO = new ResultVO();
    resultVO.setSuccess(true);
    resultVO.setCode(ResultCode.SUCCESS.getCode());
    resultVO.setMessage("调用成功");
    return resultVO;
  }

  //失败的静态方法
  public static ResultVO error(){
    ResultVO resultVO = new ResultVO();
    resultVO.setSuccess(false);
    resultVO.setCode(ResultCode.ERROR.getCode());
    resultVO.setMessage("调用失败");
    return resultVO;
  }

  public ResultVO success(Boolean success){
    this.setSuccess(success);
    return this;

  }
  public ResultVO message(String message){
    this.setMessage(message);
    return this;
  }

  public ResultVO code(Integer code){
    this.setCode(code);
    return this;
  }

  public ResultVO data(String key,Object value){
    this.data.put(key,value);
    return this;
  }

  public ResultVO data(Map<String,Object> map){
    this.setData(map);
    return this;
  }
}
