package com.panDetails.databaseFactory

import com.panDetails.databaseFactory.DatabaseFactory.dbQuery
import com.panDetails.databaseFactory.tables.OcrInfoTable
import com.panDetails.models.ApiData
import org.jetbrains.exposed.sql.insert
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.UUID


suspend fun insertData(urlData : ApiData) = dbQuery {
    val asiaTimeZone = ZoneId.of("Asia/Kolkata")
    val zoneDateTime = ZonedDateTime.now(asiaTimeZone)
    val timeStamp = zoneDateTime.toLocalTime().toString()

    OcrInfoTable.insert {
        it[this.userInfoId] = UUID.fromString("d9312522-1f5c-4ea8-a8dd-3f62f56f88e3")
        it[this.attempts] = 0
        it[this.docFileId] = UUID.fromString("d9312522-1f5c-4ea8-a8dd-3f62f56f88e3")
        it[this.createdAt] = timeStamp
        it[this.updatedAt] = timeStamp
        it[this.isManuallyVerified] = true
        it[this.createdBy] = "SOME_USER"
        it[this.panNumber] = urlData.result[0].details.pan_no.value
        it[this.panName] =  urlData.result[0].details.name.value +" "+ urlData.result[0].details.father.value
        it[this.dateOfBirth] = urlData.result[0].details.date.value
        it[this.status] = urlData.status
        it[this.updatedBy] = "SOME_USER"
    }
}


