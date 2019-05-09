package com.sxkj.de.config.scheduled;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

/**
 * 任务池配置
 *
 * @author NewGr8Player
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "spring.task")
public class ScheduledTaskProperties {

    private int poolSize = 20;

    private String threadNamePrefix = "taskExecutor-";

    private boolean waitForTasksToCompleteOnShutdown = true;

    private int awaitTerminationSeconds = 60;

    @Override
    public String toString() {
        return new StringJoiner(", ", ScheduledTaskProperties.class.getSimpleName() + "[", "]")
                .add("poolSize=" + poolSize)
                .add("threadNamePrefix='" + threadNamePrefix + "'")
                .add("waitForTasksToCompleteOnShutdown=" + waitForTasksToCompleteOnShutdown)
                .add("awaitTerminationSeconds=" + awaitTerminationSeconds)
                .toString();
    }
}
