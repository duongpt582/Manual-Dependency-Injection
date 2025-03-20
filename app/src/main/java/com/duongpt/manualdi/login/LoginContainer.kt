package com.duongpt.manualdi.login

class LoginContainer(val userRepository: UserRepository) {
    val loginData = LoginUserData()

    val loginViewModelFactory = LoginViewModelFactory(userRepository)

}

class LoginUserData {

}
