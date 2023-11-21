package com.bookstore

import com.bookstore.repositoryTesting.RepositoryTesting
import com.bookstore.routeTesting.RouteTesting
import com.bookstore.serviceTesting.ServiceTesting
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses( RouteTesting::class,
    ServiceTesting::class,
    RepositoryTesting::class
)
class TestSuiteClass
