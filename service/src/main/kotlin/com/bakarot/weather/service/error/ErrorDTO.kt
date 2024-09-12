package com.bakarot.weather.service.error

class ErrorDTO {
    val timeStamp: Long = System.currentTimeMillis()
    var status: Int = 0
    var errors: MutableList<String> = mutableListOf()
    var path: String = ""
}