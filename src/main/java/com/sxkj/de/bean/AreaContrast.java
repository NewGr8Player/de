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

import java.util.Date;

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
@TableName("de_area_contrast")
public class AreaContrast {

    /**
     * 行政区划对照表
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField
    private String code;
    @TableField
    private String name;
    @TableField
    private String localCode;
    @TableField
    private String localName;

    @TableField
    private Date updateDate;

    @TableField
    private Date createDate;

}
