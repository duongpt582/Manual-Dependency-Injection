package com.duongpt.manualdi

import com.duongpt.manualdi.login.LoginContainer
import com.duongpt.manualdi.login.LoginRetrofitService
import com.duongpt.manualdi.login.LoginService
import com.duongpt.manualdi.login.LoginViewModelFactory
import com.duongpt.manualdi.login.UserLocalDataSource
import com.duongpt.manualdi.login.UserRemoteDataSource
import com.duongpt.manualdi.login.UserRepository
import retrofit2.Retrofit

class AppContainer {
    private val loginService = Retrofit.Builder()
        .baseUrl("https://example.com")
        .build()
        .create(LoginService::class.java)

    // Then, satisfy the dependencies of UserRepository
    val remoteDataSource = UserRemoteDataSource(LoginRetrofitService(loginService))
    val localDataSource = UserLocalDataSource()

    // Now you can create an instance of UserRepository that LoginViewModel needs
    val userRepository = UserRepository(localDataSource, remoteDataSource)

//    val loginViewModelFactory = LoginViewModelFactory(userRepository)

    // LoginContainer will be null when the user is NOT in the login flow
    var loginContainer: LoginContainer? = null

}