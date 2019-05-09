package com.sxkj.de.api;

import com.sxkj.de.service.ScheduledTaskJob;
import com.sxkj.de.service.ScheduledTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务接口
 *
 * @author NewGr8Player
 */
@Api(value = "查询接口")
@RestController
@RequestMapping("/task")
public class TaskApi {

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @ApiOperation(value = "查询所有定时任务(包括未激活)", httpMethod = "GET,POST")
    @RequestMapping(path = "/searchAllTask", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, ScheduledTaskJob> searchAllTask() {
        return scheduledTaskService.findAllTask();
    }

    @ApiOperation(value = "根据taskKey启动任务", httpMethod = "GET,POST",notes="任务已经启动也会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/startByKey", method = {RequestMethod.GET, RequestMethod.POST})
    public String startByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.start(taskKey) ? "启动成功。":"启动失败，请检查任务key是否存在或者任务是否已启动。";
    }

    @ApiOperation(value = "根据taskKey停止任务", httpMethod = "GET,POST",notes="任务不存在会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/stopByKey", method = {RequestMethod.GET, RequestMethod.POST})
    public String stopByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.stop(taskKey) ? "停止成功。":"停止失败，请检查任务key是否存在或者任务是否已停止。";
    }

    @ApiOperation(value = "根据taskKey重启任务", httpMethod = "GET,POST",notes="任务不存在会返回false。")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "taskKey", value = "任务Key", paramType = "query", dataType = "String", example = "taskKey01")
    )
    @RequestMapping(path = "/restartByKey", method = {RequestMethod.GET, RequestMethod.POST})
    public String restartByKey(@Param("taskKey") String taskKey){
        return scheduledTaskService.restart(taskKey) ? "重启成功。":"重启失败，请检查任务key是否存在。";
    }


}
