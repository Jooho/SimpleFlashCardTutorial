package com.jhouse.simpleflashcard

import com.jhouse.simpleflashcard.db.JunitDaoTestSuite
import com.jhouse.simpleflashcard.manager.JunitManagerTestSuite
import com.jhouse.simpleflashcard.svc.JunitServiceTestSuite
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(JunitServiceTestSuite::class, JunitDaoTestSuite::class,JunitManagerTestSuite::class)
class JunitAndroidTestSuite