package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    companion object {
        var id = ""
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ContactsAdapter(this)

        viewModel.allContacts.observe(this) {
            adapter.setData(it)
        }

        binding.rvContactList.adapter = adapter

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

        binding.fabDeleteAllContacts.setOnClickListener {
            Toast.makeText(this, "Are you sure?", LENGTH_SHORT).show()
            binding.fabDeleteAllContactsAreYouSure.isVisible = true
        }

        binding.fabDeleteAllContactsAreYouSure.setOnClickListener {
            Toast.makeText(this, "Are you really sure?", LENGTH_SHORT).show()
            it.isVisible = false
            binding.btnDeleteAllContactsSure.isVisible = true
        }

        binding.btnDeleteAllContactsSure.setOnClickListener {
            viewModel.deleteAllContacts()
            this.recreate()
        }
    }
}