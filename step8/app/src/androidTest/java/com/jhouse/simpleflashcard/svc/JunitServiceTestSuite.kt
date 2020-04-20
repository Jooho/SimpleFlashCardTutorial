package com.jhouse.simpleflashcard.svc

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(TopicServiceTest::class, QuestionServiceTest::class,
    PlayerServiceTest::class,ConfigServiceTest::class,ResultGroupServiceTest::class,ResultServiceTest::class)
class JunitServiceTestSuite