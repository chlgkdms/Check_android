package com.example.check.data.util


import com.example.check.domain.util.BadRequestException
import com.example.check.domain.util.CheckServerException
import com.example.check.domain.util.ConflictException
import com.example.check.domain.util.ConnectionTimeOutException
import com.example.check.domain.util.ForbiddenException
import com.example.check.domain.util.MethodNotAllowedException
import com.example.check.domain.util.NotFoundException
import com.example.check.domain.util.OfflineException
import com.example.check.domain.util.ServerException
import com.example.check.domain.util.TooManyRequestException
import com.example.check.domain.util.UnAuthorizedException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RequestHandler<T> {
    suspend fun request(block: suspend () -> T): T =
        try {
            block()
        } catch (e: HttpException) {
            throw when (e.code()) {
                400 -> BadRequestException
                401 -> UnAuthorizedException
                403 -> ForbiddenException
                404 -> NotFoundException
                405 -> MethodNotAllowedException
                409 -> ConflictException
                429 -> TooManyRequestException
                502 -> CheckServerException
                in 500..599 -> ServerException
                else -> e
            }
        } catch (e: SocketTimeoutException) {
            throw ConnectionTimeOutException
        } catch (e: UnknownHostException) {
            throw OfflineException
        } catch (e: Throwable) {
            throw e
        }
}
