package com.sp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sp.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 10597
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-03-04 10:41:54
* @Entity com.sp.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




