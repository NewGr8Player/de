package com.sxkj.de.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Dao通用测试套件
 *
 * @author NewGr8Player
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ScheduledTaskDaoTest.class, UserDaoTest.class})
public class DaoBatchTest {
}
