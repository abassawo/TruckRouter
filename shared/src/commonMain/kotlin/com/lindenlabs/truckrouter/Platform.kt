package com.lindenlabs.truckrouter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform