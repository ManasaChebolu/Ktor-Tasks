package com.example

import com.example.models.Data
import com.example.models.UrlData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun filterData(schemeCodeData : UrlData,filter:String) :List<Data>{
    val currentDate = LocalDate.now()
    return when(filter) {
        "1M" -> filterDateByRange(schemeCodeData,currentDate.minusMonths(1),currentDate)
        "1W" -> filterDateByRange(schemeCodeData,currentDate.minusWeeks(1),currentDate)
        "1Y" -> filterDateByRange(schemeCodeData,currentDate.minusYears(1),currentDate)
        else -> filterDateByRange(schemeCodeData,currentDate.minusYears(5),currentDate)
    }
}

fun filterDateByRange(schemeCodeData: UrlData, startDate: LocalDate, endDate: LocalDate) :List<Data> {
    return schemeCodeData.data.filter { item ->
        val itemDate = LocalDate.parse(item.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        itemDate.isAfter(startDate) && itemDate.isBefore(endDate)
    }
}
