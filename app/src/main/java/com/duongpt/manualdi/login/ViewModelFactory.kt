package com.duongpt.manualdi.login

// Definition of a Factory interface with a function to create objects of a type
interface Factory<T> {
    fun create(): T
}

class LoginViewModelFactory(private val userRepository: UserRepository) : Factory<LoginViewModel> {
    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }

}
