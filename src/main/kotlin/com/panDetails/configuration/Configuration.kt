package com.panDetails.configuration

import com.panDetails.models.ConfigParameters
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException

object Configuration {
    lateinit var env : ConfigParameters
    fun init(environment: ApplicationEnvironment){
        env = ConfigParameters(
            url = environment.config.validateAndGetString("storage.url"),
            driver = environment.config.validateAndGetString("storage.driver"),
            user = environment.config.validateAndGetString("storage.user"),
            password = environment.config.validateAndGetString("storage.pass")
        )
    }
}

fun ApplicationConfig.validateAndGetString(key: String): String {
    val property = propertyOrNull(key)?.getString()?.trim()
    if (property.isNullOrBlank()) {
        throw ApplicationConfigurationException("$key getting null or Empty from config")
    } else {
        return property
    }
}