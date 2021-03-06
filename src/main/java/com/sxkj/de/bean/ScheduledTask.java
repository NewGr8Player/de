package com.sxkj.de.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sxkj.de.service.ScheduledTaskJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;

import java.util.StringJoiner;

/**
 * 定时任务Bean
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@TableName("de_scheduled_task")
public class ScheduledTask implements ScheduledTaskJob {

    /**
     * 任务Id
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 任务key值
     */
    @TableField
    private String taskKey;

    /**
     * 任务描述
     */
    @TableField
    private String taskDesc;

    /**
     * 任务表达式
     */
    @TableField
    private String taskCron;

    /**
     * httpMethod(Get/Post)
     */
    @TableField
    private String httpMethod;

    /**
     * 任务访问的Url
     */
    @TableField
    private String taskUrl;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    @TableField
    private String initStartFlag;

    @TableField(exist = false)
    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    @Override
    public void run() {
        try {
            String result;
            switch (httpMethod.toUpperCase()) {
                case "POST":
                    result = restTemplate.postForObject(taskUrl, "", String.class);
                    break;
                case "GET":
                    result = restTemplate.getForObject(taskUrl, String.class);
                    break;
                default:
                    result = "Failed";
                    log.error("Unsupported http method,source:{}", this);
            }
            log.info("Request:{},Result:{}", this, result);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常类型:{},异常信息:{}", e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ScheduledTask.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("taskKey='" + taskKey + "'")
                .add("taskDesc='" + taskDesc + "'")
                .add("taskCron='" + taskCron + "'")
                .add("taskUrl='" + taskUrl + "'")
                .add("initStartFlag='" + initStartFlag + "'")
                .toString();
    }
}
