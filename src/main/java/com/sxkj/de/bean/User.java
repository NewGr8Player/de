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

import java.util.HashSet;
import java.util.Set;

/**
 * 用户Bean
 *
 * @author NewGr8player
 */
@Getter
@Setter
@Accessors(chain = true)
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
    private Set<String> roles = new HashSet<>();    //用户所有角色值，用于shiro做角色权限的判断
    @TableField(exist = false)
    private Set<String> perms = new HashSet<>();    //用户所有权限值，用于shiro做资源权限的判断

    {
        roles.add( "role");
        perms.add("perm");
    }
}
