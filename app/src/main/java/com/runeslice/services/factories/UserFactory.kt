package com.runeslice.services.factories

import android.content.Context
import com.runeslice.R
import com.runeslice.models.Boss
import com.runeslice.models.Clue
import com.runeslice.models.Skill
import com.runeslice.models.User

class UserFactory(var context: Context) {
    val skillNames = context.resources.getStringArray(R.array.skills)
    val scrollNames = context.resources.getStringArray(R.array.clues)
    val bossNames = context.resources.getStringArray(R.array.bosses)

    fun createUser(
        username: String,
        response: String,
    ): User {
        val elements = response.lines().map { it.split(",").toMutableList() }

        elements.forEach { row ->
            row.forEachIndexed { index, value ->
                if (value == "-1") {
                    row[index] = "0"
                }
            }
        }

        val userSkillElements = (0..23).map {
            Skill(
                skillNames[it],
                elements[it][0].toInt(),
                elements[it][1].toInt(),
                elements[it][2].toLong()
            )
        }.toMutableList()

        val userScrollElements = (31..36).map {
            Clue(
                scrollNames[it - 31],
                elements[it][0].toInt(),
                elements[it][1].toInt()
            )
        }.toMutableList()

        val userBossElements = (42 until elements.size - 1).map {
            Boss(
                bossNames[it - 42],
                elements[it][0].toInt(),
                elements[it][1].toInt()
            )
        }.toMutableList()

        return User(username, userSkillElements, userBossElements, userScrollElements)
    }
}