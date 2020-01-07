package ru.skillbranch.devintensive.utils


object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)

        firstName = when(firstName?.length) {
            0 -> null
            else -> firstName
        }
        lastName = when(lastName?.length) {
            0 -> null
            else -> lastName
        }

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("In dev")
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var first = firstName?.trim()
        var last = lastName?.trim()

        first = when(first?.length) {
            0 -> null
            else -> first
        }
        last = when(last?.length) {
            0 -> null
            else -> last
        }

        val initials = when {
            first === null && last === null -> null
            first === null && last !== null -> last.substring(0, 1).toUpperCase()
            first !== null && last === null -> first.substring(0, 1).toUpperCase()
            else -> "${first?.substring(0, 1)?.toUpperCase()}${last?.substring(0, 1)?.toUpperCase()}"
        }


        return initials
    }
}