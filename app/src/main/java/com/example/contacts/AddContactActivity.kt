package com.example.contacts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.databinding.ActivityAddContactBinding
import com.example.contacts.ext.hideKeyboard

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etName.requestFocus()

        binding.btnSave.setOnClickListener {
            with(binding) {
                viewModel.addContact(
                     name = etName.text.toString(),
                     surname = etSurname.text.toString(),
                     phoneNumber = etNumber.text.toString()
                )
                hideKeyboard()
                startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
            }
        }

    }
}