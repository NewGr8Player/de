package com.sxkj.de.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Service通用测试套件
 *
 * @author NewGr8player
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ScheduledTaskServiceTest.class,UserServiceTest.class})
public class ServiceBatchTest {
}
