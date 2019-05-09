package com.sxkj.de.api;

import com.sxkj.de.bean.ScheduledTask;
import com.sxkj.de.service.ScheduledTaskService;
import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务接口
 *
 * @author NewGr8Player
 */
@Api(value = "查询接口")
@RestController
@RequestMapping("/api/task")
public class TaskApi {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @ApiOperationSort(100)
    @ApiOperation(value = "查询所有定时任务(包括未激活)", httpMethod = "POST")
    @RequestMapping(path = "/searchAllTask", method = {RequestMethod.POST})
    public List<ScheduledTask> searchAllTask() {
        return scheduledTaskService.taskList(new ScheduledTask());
    }
    @ApiOperationSort(101)
    @ApiOperation(value = "查询所有运行中定时任务", httpMethod = "POST")
    @RequestMapping(path = "/searchAllRunningTask", method = {RequestMethod.POST})
    public Map<String, ScheduledFuture> searchAllRunningTask() {
        return scheduledTaskService.findAllRunningTask();
    }

    @ApiOperationSort(200)
    @ApiOperation(value = "根据taskKey启动任务", httpMethod = "POST",notes="任务已经启动也会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/startByKey", method = { RequestMethod.POST})
    public String startByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.start(taskKey) ? "启动成功。":"启动失败，请检查任务key是否存在或者任务是否已启动。";
    }

    @ApiOperationSort(300)
    @ApiOperation(value = "根据taskKey停止任务", httpMethod = "POST",notes="任务不存在会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/stopByKey", method = { RequestMethod.POST})
    public String stopByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.stop(taskKey) ? "停止成功。":"停止失败，请检查任务key是否存在或者任务是否已停止。";
    }

    @ApiOperationSort(400)
    @ApiOperation(value = "根据taskKey重启任务", httpMethod = "POST",notes="任务不存在会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/restartByKey", method = {RequestMethod.POST})
    public String restartByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.restart(taskKey) ? "重启成功。":"重启失败，请检查任务key是否存在。";
    }

    @ApiOperationSort(500)
    @ApiOperation(value = "初始化任务", httpMethod = "POST",notes="任务不存在会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "scheduledTask", value = "任务条件", paramType = "query", dataType = "ScheduledTask", example = "taskKey01")
    )
    @RequestMapping(path = "/init", method = {RequestMethod.POST})
    public String init(@Param("scheduledTask") ScheduledTask scheduledTask){
        try{
            scheduledTaskService.initAllTask(scheduledTaskService.taskList(scheduledTask));
            return "初始化成功。";
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
