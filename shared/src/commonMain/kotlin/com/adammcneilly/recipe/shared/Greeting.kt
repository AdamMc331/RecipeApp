package com.adammcneilly.recipe.shared

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello Rewrite, ${platform.name}!"
    }
}
