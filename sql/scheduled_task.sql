CREATE TABLE `scheduled_task` (
  `id` varchar(50) NOT NULL,
  `task_key` varchar(128) NOT NULL COMMENT '任务key值（使用bean名称）',
  `task_desc` varchar(128) DEFAULT NULL COMMENT '任务描述',
  `task_cron` varchar(128) NOT NULL COMMENT '任务表达式',
  `init_start_flag` int(2) NOT NULL DEFAULT '1' COMMENT '程序初始化是否启动 1 是 0 否',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqu_task_key` (`task_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;