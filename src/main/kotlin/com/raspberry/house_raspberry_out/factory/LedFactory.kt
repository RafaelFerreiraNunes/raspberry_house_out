package com.raspberry.house_raspberry_out.factory

import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import org.springframework.stereotype.Component

@Component
class LedFactory(private val pi4jContext: Context) {

    fun createLed(pinAddress: Int): DigitalOutput {
        val ledConfig = DigitalOutput.newConfigBuilder(pi4jContext)
            .id("led-pino-$pinAddress")
            .name("LED de Saida")
            .address(pinAddress)
            .shutdown(DigitalState.LOW)     // Garante que apague no stop do app
            .initial(DigitalState.LOW)      // Estado inicial ao subir o app
            .provider("gpiod-digital-output") // FORÇA o uso do driver gpiod (Essencial p/ Pi 5)
            .build()

        return pi4jContext.create(ledConfig)
    }
}