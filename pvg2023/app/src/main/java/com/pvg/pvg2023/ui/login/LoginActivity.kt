package com.pvg.pvg2023.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.pvg.pvg2023.R
import com.pvg.pvg2023.base.api.CallBack
import com.pvg.pvg2023.base.api.LoginAPI
import com.pvg.pvg2023.base.api.User
import com.pvg.pvg2023.databinding.ActivityLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * ログイン画面
 */
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    /** Pass EditText */
    private lateinit var passEditText: EditText
    /** Visibility Button */
    private lateinit var visibilityButton: ImageButton
    /** パスワード表示・非表示フラグ */
    private var isVisibilityPass = false

    //private val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    /** ログインボタン */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /// Pass EditText
        passEditText = binding.editTextPass
        passEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        /// パスワード表示・非表示ボタン
        visibilityButton = binding.imageButtonVisibility
        visibilityButton.setBackgroundResource(R.color.transparent)
        visibilityButton.setOnClickListener {
            onClickVisibility()
        }
        /// ログインボタン
        val loginButton = binding.buttonLogin
        loginButton.setOnClickListener {
            onClickLoginButton()
        }
    }

    /**
     * ログインボタンタップ処理
     */
    private fun onClickLoginButton() {
        val loginApi = LoginAPI(this, "12345", "password")
        GlobalScope.launch(Dispatchers.Main) {
            loginApi.post(object : CallBack<User> {
                override fun onSuccess(response: User) {
                    TODO("Not yet implemented")
                }

                override fun onFailed(error: User) {
                    TODO("Not yet implemented")
                }

            })
        }
        //        val intent = Intent(this@LoginActivity, MainActivity::class.java)
//        startActivity(intent)
//        finish()
    }

    /**
     * パスワードの表示・非表示
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun onClickVisibility() {
        isVisibilityPass = !isVisibilityPass

        if (isVisibilityPass) {
            /// パスワード表示
            visibilityButton.setImageDrawable(getDrawable(R.drawable.ic_visibility_off_24))
            passEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            /// パスワード非表示
            visibilityButton.setImageDrawable(getDrawable(R.drawable.ic_visibility_24))
            passEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
}
