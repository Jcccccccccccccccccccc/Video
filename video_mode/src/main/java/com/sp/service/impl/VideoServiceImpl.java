package com.sp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.sp.mapper.VideoMapper;
import com.sp.pojo.QiniuProperties;
import com.sp.pojo.Video;
import com.sp.pojo.vo.ResultVO;
import com.sp.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author 10597
 * @description 针对表【video】的数据库操作Service实现
 * @createDate 2023-03-04 10:42:56
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService {
  @Autowired
  private QiniuProperties qiniuProperties;


  @Override
  public ResultVO getVideo() {
    List<Video> list = this.list();
    Collections.shuffle(list);
    return ResultVO.ok().data("videos", list);
  }

  @Override
  public String uploadVideo(MultipartFile file) throws IOException {
    // 初始化七牛云配置
    Configuration configuration = new Configuration(Region.huabei());
    Auth auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
    UploadManager uploadManager = new UploadManager(configuration);
    // 获取上传文件信息
    String originalFilename = file.getOriginalFilename();
    String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    String key = UUID.randomUUID().toString() + suffix; // sakdkas-dssaldsa -sadas ,

    // 上传到七牛云
    byte[] bytes = file.getBytes();
    String uploadToken = auth.uploadToken(qiniuProperties.getBucket(), key);
    Response response = uploadManager.put(bytes, key, uploadToken);

    if (response.isOK()) {
      return qiniuProperties.getProxyUrl() + key; // 返回文件 URL，假设使用了七牛云的 CDN 加速 http://www.baidu.com/hsadada-sdssd-sads-tu.png
    } else {
      throw new IOException("Failed to upload video to Qiniu Cloud");
    }
  }
}




