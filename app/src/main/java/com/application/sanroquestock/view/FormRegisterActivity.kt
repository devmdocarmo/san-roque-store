package com.application.sanroquestock.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.application.sanroquestock.R
import com.application.sanroquestock.repositories.UsersDatabase
import com.application.sanroquestock.model.BaseActivity
import com.application.sanroquestock.model.EntityUser
import kotlinx.android.synthetic.main.activity_form_register.*


class FormRegisterActivity : BaseActivity() {

    var userdb : UsersDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_register)
        userdb = UsersDatabase.getAppDataBase(applicationContext)

        edit_new_username.addTextChangedListener (
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    button_save_user.isEnabled = validateValues()

                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

        edit_new_password.addTextChangedListener (
                object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        button_save_user.isEnabled = validateValues()
                    }

                    override fun afterTextChanged(s: Editable?) {
                    }
                })

        button_save_user.setOnClickListener {
            Thread {
                userdb?.userDao()?.insertUser(EntityUser(0, edit_new_username.text.toString(), edit_new_password.text.toString()))
                this@FormRegisterActivity.finish()
            }.start()
        }

        button_close.setOnClickListener {
            this@FormRegisterActivity.finish()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        this@FormRegisterActivity.finish()
    }

    private fun validateValues()= (edit_new_username.text.toString().length>=4 && edit_new_password.text.toString().length>=4)
}

