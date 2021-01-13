package com.application.sanroquestock.view

import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Switch
import com.application.sanroquestock.R
import com.application.sanroquestock.model.BaseActivity

class LoginActivity : BaseActivity() {

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var switchps : Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById(R.id.edit_usuername)
        password = findViewById(R.id.edit_password)
        switchps = findViewById(R.id.switch_show_pass)

        switchps.setOnClickListener {
            if(switchps.isChecked)
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else
                password.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
            password.setSelection(password.length())
        }

    }
}