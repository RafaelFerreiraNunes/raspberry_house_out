package com.raspberry.house_raspberry_out.service

import com.pi4j.io.gpio.digital.DigitalOutput
import com.raspberry.house_raspberry_out.factory.LedFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LedService(private val ledFactory: LedFactory) {

    private val logger = LoggerFactory.getLogger(LedService::class.java)

    private val leds: MutableList<DigitalOutput> = mutableListOf()
    val pins = intArrayOf(18,23,24,25,8,7)

    init{
        logger.info("Inicializando pinos de LED via Pi4J...")
        pins.forEach { pin ->
            try {
                leds.add(ledFactory.createLed(pin))
                logger.info("Pino $pin configurado com sucesso.")
            } catch (e: Exception) {
                logger.error("Erro ao configurar pino $pin: ${e.message}")
            }
        }
    }

    fun changeStatus(ledNumber: Int) {

        val index = ledNumber - 1

        if (index in leds.indices) {
            val led = leds[index]
            led.toggle() // O Pi4J v2 gerencia o estado interno perfeitamente
            logger.info("LED $ledNumber (Pino ${led.address()}) alterado para: ${led.state()}")
        } else {
            logger.warn("Tentativa de acessar LED inválido: $ledNumber")
        }
    }

}