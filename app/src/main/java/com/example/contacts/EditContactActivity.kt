package com.example.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.MainActivity.Companion.id
import com.example.contacts.databinding.ActivityEditContactBinding

class EditContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val intent = Intent(Intent.ACTION_DIAL)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            etEditName.hint = viewModel.getContact(id)?.name.toString()
            etEditSurname.hint = viewModel.getContact(id)?.surname.toString()
            etEditNumber.hint = viewModel.getContact(id)?.phoneNumber.toString()
        }

        binding.btnSaveChanges.setOnClickListener {
            viewModel.editContact(
                id = id,
                name = isChanged(this.binding.etEditName.text.toString(), viewModel.getContact(id)?.name.toString()),
                surname = isChanged(this.binding.etEditSurname.text.toString(), viewModel.getContact(id)?.surname.toString()),
                phoneNumber = isChanged(this.binding.etEditNumber.text.toString(), viewModel.getContact(id)?.phoneNumber.toString()),
            )
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

    private fun isChanged(newValue: String, oldValue: String) = newValue.ifEmpty { oldValue }
}