package com.example.database.tables

import org.jetbrains.exposed.sql.Table

const val VAR_LENGTH = 1500
object SchemeDataTable : Table("schemeData") {
    var schemeCode = integer("schemeCode")
    var schemeName = varchar("schemeName", VAR_LENGTH)
}







