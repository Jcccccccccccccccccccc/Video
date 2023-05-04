package com.sp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    @TableId(type=IdType.AUTO)
    private Integer videoId;

    private String videoUrl;

    private static final long serialVersionUID = 1L;
}