package com.example.mylogin5b

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5b.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Creamos la conexion a la BD
        val dbHelper = DBHelperUsuario(this)

        // Click al boton Login
        binding.btnLogin.setOnClickListener{
            // Tomamos los valors de las cajas de texto
            val loginInput = binding.txtUsuario.getText().toString()
            val passInput = binding.txtPassword.getText().toString()

            // Abrimos la BD para solo lectura
            val db = dbHelper.readableDatabase

            // Creamos los argumentos para la consulta como array
            val selectionArgs = arrayOf(loginInput, passInput)

            // Creamos variable cursor para ejecutar la consulta
            val cursor = db.rawQuery("SELECT * FROM usuarios WHERE userLogin = ?  AND userPass = ?", selectionArgs)

            // Verificamos si se encontro ocurrencia en la consulta, moviendo el cursor al inicio
            if (cursor.moveToFirst()){
                Toast.makeText(this,"El usuario es correcto :-)", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this,"Usuario invalido :-(", Toast.LENGTH_SHORT).show()
            }
            // Cerramos el cursor
            cursor.close()

            // Cerramos la BD
            db.close()
        }

        binding.tvRegistrarse.setOnClickListener {
            val intentRegistrar = Intent(this, MainActivityRegistrar::class.java)
            startActivity(intentRegistrar)
            //Toast.makeText(this, "Registrarse presionado", Toast.LENGTH_SHORT).show()
        }
    }
}