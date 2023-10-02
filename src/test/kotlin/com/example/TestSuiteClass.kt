package com.example

import com.example.testing.RepositoryTesting
import com.example.testing.RouteTesting
import com.example.testing.ServiceTesting
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    RouteTesting::class,
    RepositoryTesting::class,
    ServiceTesting::class
)
class TestSuiteClass
