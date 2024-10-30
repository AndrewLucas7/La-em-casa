package br.laemcasa.auth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.laemcasa.MainActivity
import br.laemcasa.R
import br.laemcasa.api.authrization.AuthApiService
import br.laemcasa.model.error.ErrorResponse
import br.laemcasa.model.register.RegisterRequest
import br.laemcasa.model.register.RegisterResponse
import br.laemcasa.network.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import android.util.Log
import androidx.appcompat.widget.Toolbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var toolbar: Toolbar
    private lateinit var apiService: AuthApiService
    private lateinit var progressDialog: ProgressDialog

    private suspend fun makeRegisterRequest(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return withContext(Dispatchers.IO) {
            apiService.register(registerRequest).execute()
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        emailEditText = findViewById(R.id.edtEmailRegister)
        usernameEditText = findViewById(R.id.edtUsernameRegister)
        passwordEditText = findViewById(R.id.edtPasswordRegister)
        registerButton = findViewById(R.id.btnRegisterRegister)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val retrofit = RetrofitClient.getInstance()
        apiService = retrofit.create(AuthApiService::class.java)

        progressDialog = ProgressDialog(this).apply {
            setMessage("Cadastrando, por favor aguarde...")
            setCancelable(false)
        }

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val registerRequest = RegisterRequest(email, username, password)

            progressDialog.show()

            CoroutineScope(Dispatchers.Main).launch {
                val response = makeRegisterRequest(registerRequest)
                progressDialog.dismiss()

                if (response.isSuccessful) {
                    Toast.makeText(this@RegisterActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                    Log.i("RegisterActivity", "Usuário iniciou o cadastro")
                    val intentMain = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intentMain)
                    finish()
                } else {
                    val errorBody = response.errorBody()?.string()

                    val errorResponse = errorBody?.let {
                        Gson().fromJson(it, ErrorResponse::class.java)
                    }
                    val errorMessage = errorResponse?.message ?: "Erro desconhecido"
                    Toast.makeText(this@RegisterActivity, "Erro: $errorMessage", Toast.LENGTH_SHORT).show()
                    Log.e("API", "Falha na requisição de login: $errorMessage")
                }
            }
        }
    }
}