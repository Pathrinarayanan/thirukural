package com.example.kuralify.model

data class LessonItem(
    val title: String,
    val kuralIds: List<kuralCatchyData>,
    val description: String
)
data class kuralCatchyData(
    val kuralId : Int,
    val quote : String?=""
)

val leaderLessons = listOf(


//    LessonItem(
//        title = "Compassion and Kindness",
//        kuralIds = listOf(10, 11, 12),
//        description = "Being kind and empathetic defines true humanity."
//    ),
    LessonItem(
        title = "Value of Friendship",
        kuralIds = listOf(
            kuralCatchyData(809, "\uD83E\uDD1D “The world respects those who never ditch real ones.”\n" +
                    "\uD83D\uDCAF Loyalty isn’t old school — it’s legendary."),
            kuralCatchyData(789, "\uD83D\uDCAC “Real friendship stands strong — through every challenge, always there to lift you.”\n" +
                    "\uD83D\uDD17 True friends are your unwavering support, no matter the situation.\n" +
                    "\n"),
            kuralCatchyData(839, "\uD83D\uDE04 “Friendships with fools are sweet — until it’s time to part ways.”\n" +
                    "\uD83E\uDD2D No drama, just good vibes… until it’s over!"),

            ),
        description = "True friends uplift, guide, and stay loyal in adversity."
    ),
    LessonItem(
        title = "Love fades and unexplained goodbyes",
        kuralIds = listOf(
            kuralCatchyData(1278, "\uD83D\uDC94 “When love leaves, even time can’t heal the wound quickly.”\n" +
                    "⏳ It’s not just a day, it’s an ache that lingers."),
            kuralCatchyData(1178, "\uD83D\uDC94 “When the one who once loved me is gone, my eyes can't find peace without them.”\n" +
                    "\uD83D\uDE22 The absence of true love leaves a void no one else can fill."),
            kuralCatchyData(1310, "\uD83D\uDC94 “When love leaves in silence, my heart can’t help but long for the connection we once had.”\n" +
                    "\uD83D\uDCAD The unfulfilled desire for what once was lingers in the soul."),

        ),
        description = "Embracing inner calm and understanding helps prevent bitterness and regret, allowing space for healing and growth.\""
    ),
    LessonItem(
        title = "Betrayal of Friends cuz of Selfishness",
        kuralIds = listOf(
            kuralCatchyData(807, "\uD83D\uDC96 “True friends, even when betrayed, never turn away from love. Their bond remains unshaken, no matter the hurt.”\n" +
                    "\uD83C\uDF3F Friendship rooted in love transcends any betrayal."),
            kuralCatchyData(805, "\uD83D\uDCA1 “When friends cause pain, it’s not out of malice but often out of carelessness or ignorance. True understanding helps us forgive.”\n" +
                    "\uD83D\uDCAD Friends may hurt us, but understanding their intentions can heal the wounds.\n" +
                    "\n"),
            kuralCatchyData(804, "\uD83D\uDC96 “True friends, even when acting without being asked, do so with a loving heart. They embrace these gestures with kindness and gratitude.”\n" +
                    "\uD83C\uDF3F True friendship is built on thoughtful actions, even those unspoken."),

        ),
        description = " Betrayal from those we consider close to us often feels like a wound that cuts deeper than any other."
    ),
    LessonItem(
        title = "Value of Education",
        kuralIds = listOf(
            kuralCatchyData(400, "Forget gold. Knowledge is the only flex that never fades."),
            kuralCatchyData(717, "Knowledge shines brightest when you speak with clarity and class."),
            kuralCatchyData(61, "No legacy is richer than raising wise children"),
        ),
        description = "True wisdom lies in learning and applying knowledge."
    ),
    LessonItem(
        title = "Self-Control and Discipline",
        kuralIds = listOf(
            kuralCatchyData(122, "Self-control is the real flex — guard it like treasure.\nNo grind, no gold shines brighter than discipline."),
            kuralCatchyData(596, "Always think big. Even if you miss, never shrink your vision"),
            kuralCatchyData(123, "\uD83E\uDDE0 True power is knowing when to speak and mastering when to stay silent.”\n" +
                    "\uD83D\uDD25 Wisdom + Self-control = Unshakable greatness."),
        ),
        description = "Mastering the mind is key to a peaceful life."
    ),
) 