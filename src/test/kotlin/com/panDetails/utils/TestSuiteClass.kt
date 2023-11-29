package com.panDetails.utils

import com.panDetails.repositoryTesting.RepositoryTesting
import com.panDetails.routeTesting.RouteTesting
import com.panDetails.serviceTesting.ServiceTesting
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    RouteTesting::class,
    ServiceTesting::class,
    RepositoryTesting::class
)
class TestSuiteClass

