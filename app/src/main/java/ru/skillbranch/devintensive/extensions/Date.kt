package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.ceil

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND {
        override fun plural(value: Long): String {
            return when {
                value / 10 == 1L -> "секунд"
                value % 10 == 1L -> "секунду"
                value % 10 in 2L..4L -> "секунды"
                else -> "секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Long): String {
            return when {
                value / 10 == 1L -> "минут"
                value % 10 == 1L -> "минуту"
                value % 10 in 2L..4L -> "минуты"
                else -> "минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Long): String {
            return when {
                value / 10 == 1L -> "часов"
                value % 10 == 1L -> "час"
                value % 10 in 2L..4L -> "часа"
                else -> "часов"
            }
        }
    },
    DAY {
        override fun plural(value: Long): String {
            return when {
                value / 10 == 1L -> "дней"
                value % 10 == 1L -> "день"
                value % 10 in 2L..4L -> "дня"
                else -> "дней"
            }
        }
    };

    abstract fun plural(value: Long): String
}

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(): String {
    var diff = 0L
    val now = Date().time
    var isPastTime = true

    if (this.time > now) {
        diff = this.time - now
        isPastTime = false

    } else if (this.time < now) {
        diff = now - this.time
    }

    val seconds = ceil(diff.toDouble() / 1000).toLong()
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24


    return when {
        days > 365 -> if (isPastTime) "более года назад" else "более чем через год"
        days > 0 -> if (isPastTime) "$days " + TimeUnits.DAY.plural(days) + " назад" else "через " + "$days " + TimeUnits.DAY.plural(days)
        hours > 0 -> if (isPastTime) "$hours " +  TimeUnits.HOUR.plural(hours) + " назад" else "через " + "$hours " +  TimeUnits.HOUR.plural(hours)
        minutes > 0 -> if (isPastTime) "$minutes " +  TimeUnits.MINUTE.plural(minutes) + " назад" else "через " + "$minutes " +  TimeUnits.MINUTE.plural(minutes)
        seconds > 0 -> if (isPastTime) "$seconds " +  TimeUnits.SECOND.plural(seconds) + " назад" else "через " + "$minutes " +  TimeUnits.MINUTE.plural(minutes)

        else -> "только что"
    }
}