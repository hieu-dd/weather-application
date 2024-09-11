package com.bakarot.weather.service.error

class ErrorDTO {
    val timeStamp: Long = System.currentTimeMillis()
    var status: Int = 0
    var error: String = ""
    var message: String = ""
    var path: String = ""
}