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
    private String local_code;
    @TableField
    private String local_name;

    @TableField("update_date")
    private String updateDate;

    @TableField("create_date")
    private String createDate;

}
