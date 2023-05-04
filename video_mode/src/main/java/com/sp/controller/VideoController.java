package com.sp.controller;

import com.sp.pojo.Video;
import com.sp.pojo.vo.ResultVO;
import com.sp.service.CommentService;
import com.sp.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 视频播放相关接口
 * @Author: sp
 * @Date: 2023/3/4 11:02
 */
@RestController
@RequestMapping("/video")
@CrossOrigin
public class VideoController {
  @Autowired
  private VideoService service;

  /**
   * @return com.sp.pojo.vo.ResultVO
   * @Description 获取视频接口
   * @date 2023/3/4 14:22
   */
  @GetMapping("/getVideo")
  public ResultVO getVideo() {
    return service.getVideo();
  }

  @PostMapping("/upload")
  public String uploadVideo(@RequestParam("file") MultipartFile file) {
    try {
      String filePath = service.uploadVideo(file);
      return filePath;
    } catch (Exception e) {
      e.printStackTrace();
      return "Failed to upload video to Qiniu Cloud";
    }
  }

  @PostMapping("/addVideo")
  public ResultVO addVideo(@RequestBody Video video) {
    boolean save = service.save(video);
    return save ? ResultVO.ok() : ResultVO.error();
  }
}
