package com.sxkj.de.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("de_user")
public class User {

    /**
     * 任务Id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField
    private String username;

    @TableField
    private String password;

    @TableField
    private String salt;

    @TableField(exist = false)
    private String role = "role";

    @TableField(exist = false)
    private String perm = "perm";
}
