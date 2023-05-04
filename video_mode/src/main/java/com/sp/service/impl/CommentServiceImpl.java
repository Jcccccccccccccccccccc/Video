package com.sp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sp.pojo.Comment;
import com.sp.pojo.vo.ResultVO;
import com.sp.service.CommentService;
import com.sp.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 10597
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-03-04 10:41:54
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
  @Override
  public ResultVO getComment(Integer videoId) {
    QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("video_id",videoId).orderByDesc("comment_time");
    List<Comment> comments = list(queryWrapper);
    return ResultVO.ok().data("comments",comments);
  }
}




