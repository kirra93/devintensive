package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

class Profile(
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {
    var nickname: String = "John Doe"
    var rank: String = "Junior Android Developer"

    init {
        nickname = Utils.transliteration("$firstName $lastName")
    }

    fun toMap(): Map<String, Any> = mapOf(
        "nickname" to nickname,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}