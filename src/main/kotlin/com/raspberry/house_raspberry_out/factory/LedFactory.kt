package com.raspberry.house_raspberry_out.factory

import com.diozero.devices.LED

object LedFactory {

    fun createLed(pin: Int): LED {
        return LED(pin)
    }
}