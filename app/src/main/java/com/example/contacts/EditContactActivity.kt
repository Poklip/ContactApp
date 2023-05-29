package com.example.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.MainActivity.Companion.id
import com.example.contacts.databinding.ActivityEditContactBinding
import com.example.contacts.ext.hideKeyboard

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val intent = Intent(Intent.ACTION_DIAL)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            etEditName.setText(viewModel.getContact(id)?.name.toString())
            etEditName.setSelection(etEditName.length())

            etEditSurname.setText(viewModel.getContact(id)?.surname.toString())
            etEditSurname.setSelection(etEditSurname.length())

            etEditNumber.setText(viewModel.getContact(id)?.phoneNumber.toString())
            etEditNumber.setSelection(etEditNumber.length())
        }

        binding.btnSaveChanges.setOnClickListener {
            viewModel.editContact(
                id = id,
                name = (binding.etEditName.text.toString()),
                surname = (binding.etEditSurname.text.toString()),
                phoneNumber = (binding.etEditNumber.text.toString())
            )
            hideKeyboard()
            startActivity(Intent(this@EditContactActivity, MainActivity::class.java))
            finish()
        }

        binding.btnDeleteContact.setOnClickListener {
            viewModel.deleteContact(id)
            startActivity(Intent(this@EditContactActivity, MainActivity::class.java))
            finish()
        }

        binding.btnCall.setOnClickListener {
            intent.data = Uri.parse("tel:" + viewModel.getContact(id)?.phoneNumber.toString())
            startActivity(intent)
        }
    }
}