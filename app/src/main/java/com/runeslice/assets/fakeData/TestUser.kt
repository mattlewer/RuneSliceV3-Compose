package com.runeslice.assets.fakeData

import com.runeslice.models.Boss
import com.runeslice.models.Clue
import com.runeslice.models.Skill
import com.runeslice.models.User

fun makeFakeUser(): User {
    val skillsList = mutableListOf<Skill>()
    repeat(24) {
        skillsList.add(
            Skill(
                name = "Test",
                level = (0..99).random(),
                xp = (20000..3000000).random().toLong(),
                rank = (0..200).random()
            )
        )
    }

    val bossList = mutableListOf<Boss>()
    repeat(59) {
        bossList.add(
            Boss(
                name = "Test",
                rank = (0..200).random(),
                num = (0..200).random(),
            )
        )
    }

    val clueList = mutableListOf<Clue>()
    repeat(6) {
        clueList.add(
            Clue(
                name = "Test",
                rank = (0..200).random(),
                num = (0..200).random(),
            )
        )
    }

    val fakeUser = User(
        name = getRandomString(6),
        skills = skillsList,
        boss = bossList,
        clues =  clueList,
    )
    return fakeUser
}

fun getRandomString(length: Int) : String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
