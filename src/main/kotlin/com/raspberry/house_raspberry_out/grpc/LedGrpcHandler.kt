package com.raspberry.house_raspberry_out.grpc

import net.devh.boot.grpc.server.service.GrpcService
import com.raspberry.house_raspberry_out.service.LedService

@GrpcService
class LedGrpcHandler(
    private val hardwareService: LedService
) : LedServiceGrpcKt.LedServiceCoroutineImplBase() {

    override suspend fun changeStatus(request: LedRequest): LedResponse {
        hardwareService.changeStatusLed(request.ledId)

        return LedResponse.newBuilder()
            .setSuccess(true)
            .setMessage("gRPC: LED ${request.ledId} alterado com sucesso")
            .build()
    }
}