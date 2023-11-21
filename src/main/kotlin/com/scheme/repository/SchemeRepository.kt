package com.scheme.repository

import com.scheme.database.DatabaseFactory.dbQuery
import com.scheme.database.tables.SchemeDataTable
import com.scheme.filterData
import com.scheme.httpClient.getData
import com.scheme.httpClient.getSchemeData
import com.scheme.interfaces.SchemeInterface
import com.scheme.models.ResponseData
import com.scheme.models.Response
import com.scheme.models.Scheme
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select


class SchemeRepository : SchemeInterface {

    override suspend fun getSchemeDataRepo() {
        val response = getData()
         response.forEach { scheme ->
                dbQuery {
                    SchemeDataTable.insert {
                        it[this.schemeName] = scheme.schemeName
                        it[this.schemeCode] = scheme.schemeCode
                    }
                }
            }
    }

    override suspend fun postSchemeDataRepo(schemeName: String): Scheme? = dbQuery {
        SchemeDataTable.select( SchemeDataTable.schemeName eq  schemeName)
            .map { Scheme( it[SchemeDataTable.schemeCode],
                           it[SchemeDataTable.schemeName]) }
            .singleOrNull()
    }

    override suspend fun postMetaDataRepo(schemeCode: Int, filter: String): Response {
        val schemeCodeData = getSchemeData(schemeCode)
        val filteredData = filterData(schemeCodeData, filter)
        return Response(ResponseData(schemeCodeData.meta.fund_house,
            schemeCodeData.meta.scheme_code,
            schemeCodeData.meta.scheme_name,
            listOf( filteredData.map { it.date }, filteredData.map { it.nav })
        ))
    }

}
