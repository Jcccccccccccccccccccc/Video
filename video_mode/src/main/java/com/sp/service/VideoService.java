package com.sp.service;

import com.sp.pojo.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sp.pojo.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 10597
* @description 针对表【video】的数据库操作Service
* @createDate 2023-03-04 10:42:56
*/
public interface VideoService extends IService<Video> {
  /**
   * @author sp
   * @Descriptison 获取数据库中的视频
   * @date 2023/3/4 11:16
   * @return com.sp.pojo.vo.ResultVO
   */
  ResultVO getVideo();

  String uploadVideo(MultipartFile file) throws IOException;
}
