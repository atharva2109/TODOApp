package com.team2.todo.data.datautils

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {

    /* Converts Localdatetime Object to String for saving datetime inside room db */
    @TypeConverter
    fun timeStampToString(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }

    /* Localdatetime object is obtained from datetime string  */
    @TypeConverter
    fun stringToTimeStamp(dateTimeString: String): LocalDateTime {
        return LocalDateTime.parse(dateTimeString)
    }
}