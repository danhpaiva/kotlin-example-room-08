package com.example.cadastrousuario

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cadastrousuario.repository.UsuarioDataBase

class DetalhesActivity : AppCompatActivity() {

    private lateinit var tvNome: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvTelefone: TextView
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalhes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvNome = findViewById(R.id.tvNome)
        tvEmail = findViewById(R.id.tvEmail)
        tvTelefone = findViewById(R.id.tvTelefone)
        btnVoltar = findViewById(R.id.btnVoltar)

        //Instancia o banco e seleciona o primeiro Id
        val usuarioDatabase = UsuarioDataBase.getDataBase(this).usuarioDAO()
        val retornoSelectUm = usuarioDatabase.get(1)

        // Recupera os dados passados pelo Banco
        val nome = retornoSelectUm.nome
        val email = retornoSelectUm.email
        val telefone = retornoSelectUm.telefone

        // Exibe os dados nas TextViews
        tvNome.text = "Nome: $nome"
        tvEmail.text = "Email: $email"
        tvTelefone.text = "Telefone: $telefone"

        btnVoltar.setOnClickListener {
            onBackPressed()
        }
    }
}