package com.sp.controller;

import com.sp.pojo.Comment;
import com.sp.pojo.vo.ResultVO;
import com.sp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 评论相关接口
 * @Author: sp
 * @Date: 2023/3/4 14:23
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
  @Autowired
  private CommentService commentService;

  /**
   * @return com.sp.pojo.vo.ResultVO
   * @Description 根据视频id查询视频下的所有的评论
   * @date 2023/3/4 14:31
   * @Param videoId
   */
  @GetMapping("/getComment/{videoId}")
  public ResultVO getComment(@PathVariable Integer videoId) {
    return commentService.getComment(videoId);
  }

  /**
  * @Description 保存评论
  * @date 2023/3/4 14:33
  * @Param comment
  * @return com.sp.pojo.vo.ResultVO
  */
  @PostMapping("saveComment")
  public ResultVO saveComment(@RequestBody Comment comment) {
    boolean save = commentService.save(comment);
    return save ? ResultVO.ok() : ResultVO.error();
  }
}
