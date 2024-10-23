package br.laemcasa.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.laemcasa.MainActivity
import br.laemcasa.api.authrization.AuthApiService
import br.laemcasa.R
import br.laemcasa.model.login.LoginRequest
import br.laemcasa.model.login.LoginResponse
import br.laemcasa.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var forgetpassword: TextView

    private lateinit var apiService: AuthApiService

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.edtUser)
        passwordEditText = findViewById(R.id.edtPassword)
        loginButton = findViewById(R.id.btnLogin)
        registerButton = findViewById(R.id.btnRegister)
        forgetpassword = findViewById(R.id.fgtpasstxview)

        val retrofit = RetrofitClient.getInstance()
        apiService = retrofit.create(AuthApiService::class.java)

        loginButton.setOnClickListener {
            val email = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val loginRequest = LoginRequest(email, password)

            loginButton.isEnabled = false
            registerButton.isEnabled = false
            forgetpassword.isEnabled = false

            apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        loginButton.isEnabled = true
                        registerButton.isEnabled = true
                        forgetpassword.isEnabled = true
                        val loginResponse = response.body()
                        val token = loginResponse?.token

                        if(token != null){
                            RetrofitClient.updateToken(token)
                            val intentMain = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intentMain)
                            finish()
                        }
                    } else {
                        val errorbody = response.errorBody()?.toString()
                        Toast.makeText(applicationContext,errorbody ?: "Erro Desconhecido",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginButton.isEnabled = true
                    registerButton.isEnabled = true
                    forgetpassword.isEnabled = true
                    Toast.makeText(applicationContext, t.message ?: "Usuario Desconhceido", Toast.LENGTH_SHORT).show()
                }

            })
        }

        registerButton.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
        }

    }
}


