package com.example.cadastrousuario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrousuario.model.Usuario
import com.example.cadastrousuario.repository.UsuarioDataBase

class MainActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var etEmail: EditText
    private lateinit var etTelefone: EditText
    private lateinit var btnCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNome = findViewById(R.id.etNome)
        etEmail = findViewById(R.id.etEmail)
        etTelefone = findViewById(R.id.etTelefone)
        btnCadastrar = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            cadastrarUsuario()
        }
    }

    private fun cadastrarUsuario(){
        val nome = etNome.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // Validação simples de email (pode ser mais robusta)
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, insira um email válido.", Toast.LENGTH_SHORT).show()
            return
        }

        val usuarioDatabase = UsuarioDataBase.getDataBase(this).usuarioDAO()

        usuarioDatabase.insertUser(Usuario().apply {
            this.nome = nome
            this.email = email
            this.telefone = telefone
        })

        // Cria um Intent para iniciar a DetalhesActivity
        val intent = Intent(this, DetalhesActivity::class.java).apply {
            putExtra("NOME", nome)
            putExtra("EMAIL", email)
            putExtra("TELEFONE", telefone)
        }

        startActivity(intent)

        etNome.text.clear()
        etEmail.text.clear()
        etTelefone.text.clear()
    }
}