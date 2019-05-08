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
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.client.RestTemplate;

import java.util.StringJoiner;

@Getter
@Setter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@TableName("de_scheduled_task")
public class ScheduledTask implements Job {

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
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            String result = restTemplate.postForEntity(taskUrl, jobDataMap, String.class).getBody();
            log.info(result);
        } catch (Exception e){
            e.printStackTrace();
            log.error("异常类型:{},异常信息:{}",e.getClass().getSimpleName(),e.getMessage());
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
