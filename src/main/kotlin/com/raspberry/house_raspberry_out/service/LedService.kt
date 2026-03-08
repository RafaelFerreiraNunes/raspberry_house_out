package com.raspberry.house_raspberry_out.service

import com.diozero.devices.LED
import com.raspberry.house_raspberry_out.factory.LedFactory
import org.springframework.stereotype.Service

@Service
class LedService {

    private val leds: MutableList<LED> = mutableListOf()
    val pins = intArrayOf(18,23,24,25,8,7)

    init{
        pins.forEach { pin ->
            leds.add(LedFactory.createLed(pin))
        }
    }

    fun changeStatusLed(led: Int) {
        leds[led -1].toggle()
    }

}