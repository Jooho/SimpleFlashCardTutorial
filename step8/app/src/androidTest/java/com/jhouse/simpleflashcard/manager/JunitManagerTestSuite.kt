package com.jhouse.simpleflashcard.manager

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    TopicManagerTest::class,
    PlayerManagerTest::class, ResultGroupManagerTest::class
)
class JunitManagerTestSuite