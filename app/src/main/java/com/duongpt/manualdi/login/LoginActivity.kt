package com.duongpt.manualdi.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.duongpt.manualdi.AppContainer
import com.duongpt.manualdi.MyApplication
import com.duongpt.manualdi.R
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginData: LoginUserData
    private lateinit var appContainer: AppContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // In order to satisfy the dependencies of LoginViewModel, you have to also
        // satisfy the dependencies of all of its dependencies recursively.
        // First, create retrofit which is the dependency of UserRemoteDataSource
        val loginService = Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginService::class.java)

//        // Then, satisfy the dependencies of UserRepository
//        val remoteDataSource = UserRemoteDataSource(LoginRetrofitService(loginService))
//        val localDataSource = UserLocalDataSource()
//
//        // Now you can create an instance of UserRepository that LoginViewModel needs
//        val userRepository = UserRepository(localDataSource, remoteDataSource)
//
//        // Lastly, create an instance of LoginViewModel with userRepository
//        val viewModel = LoginViewModel(userRepository)


        // Gets userRepository from the instance of AppContainer in Application
        val appContainer = (application as MyApplication).appContainer
//        loginViewModel = appContainer.loginViewModelFactory.create()

//        appContainer = (application as MyApplication).appContainer

        // Login flow has started. Populate loginContainer in AppContainer
        appContainer.loginContainer = LoginContainer(appContainer.userRepository)

        loginViewModel = appContainer.loginContainer!!.loginViewModelFactory.create()
        loginData = appContainer.loginContainer!!.loginData

    }

    override fun onDestroy() {
        // Login flow is finishing
        // Removing the instance of loginContainer in the AppContainer
        appContainer.loginContainer = null
        super.onDestroy()
    }
}

interface LoginService {
    // Define the API endpoints here
}
