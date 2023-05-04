package com.sp.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    @TableId(value = "comment_id",type = IdType.AUTO)
    private Integer commentId;

    private String nickName;

    private String content;

    private Integer videoId;

    @TableField(fill = FieldFill.INSERT)
    private Date commentTime;
    private static final long serialVersionUID = 1L;
}