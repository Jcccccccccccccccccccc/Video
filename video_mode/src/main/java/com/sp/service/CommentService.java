package com.sp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.pojo.Comment;
import com.sp.pojo.vo.ResultVO;

/**
* @author 10597
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-03-04 10:41:54
*/
public interface CommentService extends IService<Comment> {

  /**
  * @author lcf
  * @Description 根据视频id查询视频中所有的评论
  * @date 2023/3/4 14:25
  * @Param
  * @return com.sp.pojo.vo.ResultVO
  */
  ResultVO getComment(Integer videoId);

}
