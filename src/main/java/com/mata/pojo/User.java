package com.mata.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@TableName("tb_user")
public class User {
    @TableId(value="user_id", type= IdType.AUTO)
    private Integer id;
    @TableField("open_id")
    private String openId;
}
