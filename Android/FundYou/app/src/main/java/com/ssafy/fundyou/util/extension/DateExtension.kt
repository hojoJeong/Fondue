package com.ssafy.fundyou.util.extension

import java.util.Calendar
import java.util.Locale

fun getDeadLineByEndDate(endDate: Long): Int {
    val currentDay = System.currentTimeMillis()
    val deadLine = (currentDay - endDate) / (1000 * 60 * 24)
    return if (deadLine < 0) -1 else deadLine.toInt()
}