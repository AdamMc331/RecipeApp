package com.mcloo.recipes.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
