package com.sp.mapper;

import com.sp.pojo.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 10597
* @description 针对表【video】的数据库操作Mapper
* @createDate 2023-03-04 10:42:56
* @Entity com.sp.domain.Video
*/
@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}




