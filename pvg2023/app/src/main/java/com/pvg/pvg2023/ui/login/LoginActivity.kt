package com.pvg.pvg2023.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.pvg.pvg2023.MainActivity
import com.pvg.pvg2023.R
import com.pvg.pvg2023.databinding.ActivityLoginBinding

/**
 * ログイン画面
 */
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    /** ログインボタン */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginButton = binding.buttonLogin
        loginButton.setOnClickListener {
            onClickLoginButton()
        }
    }

    /**
     * ログインボタンタップ処理
     */
    private fun onClickLoginButton() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}