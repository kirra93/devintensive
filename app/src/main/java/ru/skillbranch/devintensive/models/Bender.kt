package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val validator = question.validate(answer)

        return if (validator !== null) {
            "$validator\n${question.question}" to status.color

        } else if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color

        } else {
            status = status.nextStatus()
            "${if(status.ordinal != 0) "Это не правильный ответ" else "Это неправильный ответ. Давай все по новой"}\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,255,0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                return values()[this.ordinal + 1]
            } else {
                return values()[0]
            }
        }

        fun prevStatus(): Status {
            return if (this.ordinal > 0) {
                return values()[this.ordinal - 1]
            } else {
                return values()[this.ordinal]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("bender", "бендер")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun validate(answer: String): String? = if (!answer[0].isUpperCase()) "Имя должно начинаться с заглавной буквы" else null
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun validate(answer: String): String? = if (answer[0].isUpperCase()) "Профессия должна начинаться со строчной буквы" else null
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun validate(answer: String): String? = if (answer.matches(Regex(".*\\d.*"))) "Материал не должен содержать цифр" else null
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun validate(answer: String): String? = if (!answer.matches("[0-9]+".toRegex())) "Год моего рождения должен содержать только цифры" else null
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): String? = if (answer.length != 7 || !answer.matches("[0-9]+".toRegex())) "Серийный номер содержит только цифры, и их 7" else null
        },
        IDLE("На этом все, вопросов больше нет.", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun validate(answer: String): String? = null
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): String?
    }
}