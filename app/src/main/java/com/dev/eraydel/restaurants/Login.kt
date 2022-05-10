package com.dev.eraydel.restaurants

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.dev.eraydel.restaurants.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    //private lateinit var adapter : LoginAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            login()
        }
        binding.btnRegister.setOnClickListener{
            val intent = Intent(this,Register::class.java)
            this.startActivity(intent)
        }
    }

    //login function
    private fun login(){
        if(validaForm()){
            val email: EditText = binding.email
            val password: EditText = binding.password
            auth(email.text.toString(),password.text.toString())
        } else {
            Message("¡Capture correctamente los campos!")
        }
    }


    //auth
    private fun auth(email: String, password: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<UserResponse> = getRetrofit().create(APIService::class.java).login("users")
            val allUsers:UserResponse? = call.body()

            runOnUiThread {

                if(call.isSuccessful){
                    var users: List<Users> = allUsers?.users ?: emptyList<Users>()
                    var flag: Boolean = false
                    for(user in users)
                    {
                        if( email.toString() == user.email.toString() && password.toString() == user.password.toString() )
                        {
                            flag = true
                            break
                        }
                    }

                    if( flag ) {
                        init(true)
                    } else {
                        init(false)
                    }
                } else {
                    Message("Ocurrió algún error al intentar conectar con el MockService")
                }
            }

        }

    }

    private fun init(b: Boolean) {
        if(b) {
            Message("Bienvenido ${binding.email.text.toString()}")
            val intent = Intent(this,MainActivity::class.java)
            this.startActivity(intent)
        } else {
            Message("Usuario no existe o la contraseña es incorrecta")
        }
    }

    /*
    Method: getRetrofit
    Created by: Erick Ayala
    Created at: 09/05/2022
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo0427466.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //validate form fields
    private fun validaForm() : Boolean {
        if( validateEmail() && validatePassword() ) return true
        else return false
    }

    private fun validatePassword(): Boolean {

        if( binding.password.text.toString() != "" ) return true
        else {
            binding.password.error = "Ingrese su contraseña"
            binding.password.requestFocus()
            return false
        }
    }

    private fun validateEmail(): Boolean {
        //Paso 0. Reg Pattern
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        //Paso 1. Compares Pattern with input
        if ( binding.email.text.toString().matches(emailPattern.toRegex()) ) return true
        else {
            binding.email.error = "Ingrese un correo electrónico válido"
            binding.email.requestFocus()
            return false
        }
    }

    private fun Message(message:String){
        Toast.makeText(this,"${message}", Toast.LENGTH_SHORT).show()
    }
}