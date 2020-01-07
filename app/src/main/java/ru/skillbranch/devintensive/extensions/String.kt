package ru.skillbranch.devintensive.extensions

fun String.truncate(range: Int = 16): String {
    return "${this.substring(0, range)}..."
}