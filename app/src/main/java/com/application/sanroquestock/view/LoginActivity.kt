package com.application.sanroquestock.view

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import com.application.sanroquestock.R
import com.application.sanroquestock.model.BaseActivity
import com.application.sanroquestock.model.EntityUser
import com.application.sanroquestock.repositories.UsersDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    var userdb : UsersDatabase?=null
    var username : String?= null
    var pass : String?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userdb = UsersDatabase.getAppDataBase(applicationContext)
        actionBar?.hide()
        /**********************************/
        val string = "My sensitive string that I want to encrypt"
        val encrypted = fullEncrypt(string)

//        val decrypted = fullDecrypt(encrypted)

        /**********************************/


        edit_usuername.requestFocus()
        edit_usuername.setSelection(edit_usuername.length())
        
        switch_show_pass.setOnClickListener {
            if (switch_show_pass.isChecked)
                edit_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else
                edit_password.inputType = InputType.TYPE_CLASS_TEXT or
                        InputType.TYPE_TEXT_VARIATION_PASSWORD
            edit_password.setSelection(edit_password.length())
        }

        button_login.setOnClickListener {
            edit_password.onEditorAction(EditorInfo.IME_ACTION_DONE)
            userdb?.userDao()?.findByName(edit_usuername.text.toString())?.observe(this,
                    Observer<EntityUser?> { t ->
                        username = t?.username
                        pass = t?.passEncript
                        if (pass == edit_password.text.toString()) {
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        } else {

                        }
                    })
        }

        button_register.setOnClickListener {
            val intent = Intent(this, FormRegisterActivity::class.java)
            startActivity(intent)
            onPause()
        }

            //edit_usuername.setText(userdb?.userDao()?.getAll()?.get(0)?.username.toString())
            userdb?.userDao()?.getAll()?.observe(this,
                    Observer<List<EntityUser>> { t ->
                        edit_usuername.setText(t?.get(0)?.username)
                        edit_usuername.requestFocus()
                        edit_usuername.setSelection(edit_usuername.length())
                    })

    }
}

