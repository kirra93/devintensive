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
        var nickname = ""
        var isUpper = false
        for (ch in payload) {
            isUpper = ch.isUpperCase()
            nickname += if (!isUpper) transliteLetter(ch.toUpperCase()).toLowerCase() else transliteLetter(ch.toUpperCase())
        }
        return nickname
    }

    private fun transliteLetter(letter: Char): String = when (letter) {
            'А' -> "A"
            'Б' -> "B"
            'В' -> "V"
            'Г' -> "G"
            'Д' -> "D"
            'Е' -> "E"
            'Ё' -> "JE"
            'Ж' -> "ZH"
            'З' -> "Z"
            'И' -> "I"
            'Й' -> "Y"
            'К' -> "K"
            'Л' -> "L"
            'М' -> "M"
            'Н' -> "N"
            'О' -> "O"
            'П' -> "P"
            'Р' -> "R"
            'С' -> "S"
            'Т' -> "T"
            'У' -> "U"
            'Ф' -> "F"
            'Х' -> "KH"
            'Ц' -> "C"
            'Ч' -> "CH"
            'Ш' -> "SH"
            'Щ' -> "JSH"
            'Ъ' -> "HH"
            'Ы' -> "IH"
            'Ь' -> "JH"
            'Э' -> "EH"
            'Ю' -> "JU"
            'Я' -> "JA"
            ' ' -> "_"
            else -> ""
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