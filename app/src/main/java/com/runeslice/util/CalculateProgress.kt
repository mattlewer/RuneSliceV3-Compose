package com.runeslice.util

import com.runeslice.constants.xpBreakPoints
import com.runeslice.models.Skill
import java.math.RoundingMode
import java.text.DecimalFormat

val breakPoints = xpBreakPoints

fun roundOffDecimal(number: Double): Float {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.FLOOR
    return df.format(number).toFloat()
}

fun calculatePercentThroughLevel(skill: Skill): Float {
    breakPoints.forEachIndexed { index, value ->
        if (skill.xp > breakPoints.last()) return 1f
        else if (index > 0 && breakPoints[index] > skill.xp)
            return roundOffDecimal((skill.xp.toDouble() - breakPoints[index - 1].toDouble()) / (breakPoints[index].toDouble() - breakPoints[index - 1].toDouble()))
    }
    return 0f
}

fun calculatePercentToNextTen(skill: Skill): Float {
    var percentageToNextTen = 0f;
    for (i in 0..xpBreakPoints.size - 1) {
        if (skill.xp > xpBreakPoints.last()) {
            percentageToNextTen = 1f
            break
        } else if (xpBreakPoints[i] > skill.xp && i % 10 == 0) {
            percentageToNextTen =
                roundOffDecimal((skill.xp.toDouble() - xpBreakPoints[i - 10].toDouble()) / (xpBreakPoints[i].toDouble() - xpBreakPoints[i - 10].toDouble()))
            break
        } else if (i == 99) {
            percentageToNextTen =
                roundOffDecimal((skill.xp.toDouble() - xpBreakPoints[i - 10].toDouble()) / (13034431 - xpBreakPoints[i - 10].toDouble()))
            break
        }
    }
    return percentageToNextTen
}

fun calculatePercentToNinetyNine(skill: Skill): Float{
    return roundOffDecimal(skill.xp.toDouble() / xpBreakPoints.last().toDouble())
}