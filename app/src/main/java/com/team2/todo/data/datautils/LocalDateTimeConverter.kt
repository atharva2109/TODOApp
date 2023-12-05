package com.team2.todo.data.datautils

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class LocalDateTimeConverter {

    /* Converts Localdatetime Object to String for saving datetime inside room db */
    @TypeConverter
    fun timeStampToString(dateTime: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        return dateTime.format(formatter)
    }

    /* Localdatetime object is obtained from datetime string  */
    @TypeConverter
    fun stringToTimeStamp(dateTimeString: String): LocalDateTime {
        return LocalDateTime.parse(dateTimeString)
    }
}