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
@TableName("de_receive_info")
public class ReceiveInfo {

    /**
     * 信访数据接收处理表
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("business_type")
    private String businessType;

    @TableField("business_name")
    private String businessName;

    @TableField("file_path")
    private String filePath;

    @TableField("file_name")
    private String fileName;

    @TableField("file_code")
    private String fileCode;

    @TableField("status_code")
    private String statusCode;

    @TableField("data_count")
    private String dataCount;

    @TableField("file_content")
    private String fileContent;

    @TableField("remark")
    private String remark;

    @TableField("update_date")
    private String updateDate;

    @TableField("create_date")
    private String createDate;

}
