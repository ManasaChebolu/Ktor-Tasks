package com.example

import com.example.repositoryTesting.RepositoryTesting
import com.example.routeTesting.RouteTesting
import com.example.serviceTesting.ServiceTesting
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses( RouteTesting::class,
    ServiceTesting::class,
    RepositoryTesting::class
)
class TestSuiteClass
