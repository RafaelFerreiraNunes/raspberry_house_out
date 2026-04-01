package com.raspberry.house_raspberry_out.service

import com.diozero.devices.LED
import com.raspberry.house_raspberry_out.factory.LedFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LedService {

    private val logger = LoggerFactory.getLogger(LedService::class.java)

    private val leds: MutableList<LED> = mutableListOf()
    val pins = intArrayOf(18,23,24,25,8,7)

    init{
        pins.forEach { pin ->
            leds.add(LedFactory.createLed(pin))
        }
    }

    fun changeStatus(led: Int) {
        leds[led -1].toggle()
        logger.info("Led $led changed")
    }

}