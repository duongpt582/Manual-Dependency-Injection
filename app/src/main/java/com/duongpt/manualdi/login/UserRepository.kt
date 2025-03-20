package com.duongpt.manualdi.login

import retrofit2.Retrofit

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {
    fun login() {
        // do something
    }
}

class UserLocalDataSource {}
class UserRemoteDataSource(
    private val loginService: LoginRetrofitService
)

class LoginRetrofitService(
    private val retrofit: LoginService
)