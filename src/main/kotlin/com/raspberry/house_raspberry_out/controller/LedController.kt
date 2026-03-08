package com.raspberry.house_raspberry_out.controller

import com.raspberry.house_raspberry_out.service.LedService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/led")
class LedController(
    private val ledService: LedService
) {

    @GetMapping("/{led}")
    fun sequencial(@PathVariable led: String): String {

        val posLed = led.toIntOrNull() ?: return "Error - requisição precisa ser do tipo numérico"

        if (posLed >= 0 && posLed <= ledService.pins.size) {
            ledService.changeStatusLed(posLed)
            return ""
        } else {
            return "Error - Led inválido"
        }

    }

}