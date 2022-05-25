package com.bluemsun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user")
@Data
public class User {
    private Integer id;
    //OPEN_ID
    private String openId;
    //会话密钥
    private String sessionKey;
    //头像路径
    private String avatarUrl;
    //昵称
    private String nickName;
//    @TableField(exist = false)
//    private String password;
}
