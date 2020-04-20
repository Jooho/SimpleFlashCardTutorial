package com.jhouse.simpleflashcard.db

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(TopicDaoTest::class, QuestionDaoTest::class,
    PlayerDaoTest::class,ConfigDaoTest::class,PlayerResultDaoTest::class,ResultDaoTest::class)
class JunitDaoTestSuite