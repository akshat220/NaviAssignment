package com.example.assignment

import kotlinx.coroutines.delay

object MainRepository {

    suspend fun submitData(): String {
        delay(2000)
        return "Details submitted successfully"
    }
}