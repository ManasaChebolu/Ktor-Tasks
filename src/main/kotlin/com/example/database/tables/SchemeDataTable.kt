package com.example.database.tables

import org.jetbrains.exposed.sql.Table

object SchemeDataTable : Table("schemeData") {
    var schemeCode = integer("schemeCode")
    var schemeName = varchar("schemeName", 1500)
}







