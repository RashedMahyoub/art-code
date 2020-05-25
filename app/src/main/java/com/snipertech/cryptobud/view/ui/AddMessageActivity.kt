package com.snipertech.cryptobud.view.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.snipertech.cryptobud.R
import com.snipertech.cryptobud.databinding.ActivityAddMessageBinding
import com.snipertech.cryptobud.viewModel.AddMessageViewModel
import java.security.KeyFactory
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import javax.crypto.spec.SecretKeySpec

class AddMessageActivity : AppCompatActivity() {

    private lateinit var addMessageViewModel: AddMessageViewModel
    private lateinit var binding: ActivityAddMessageBinding
    private var text = ""
    private var key = ""

    companion object {
        private const val TEXT_1 = "1"
        private const val TEXT_2 = "2"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMessageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //change the color of actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#0CC6FF")))

        //initialize view model
        addMessageViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(AddMessageViewModel::class.java)

        //setup the adapter
        val adapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.encryption_type,
            R.layout.support_simple_spinner_dropdown_item
        )
        binding.spinnerEncryptionType.adapter = adapter

        encrypt()

        decrypt()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(){
        //encryption button
        binding.encrypt.setOnClickListener {
            if(binding.plainText.text.toString() == ""){
                Toast.makeText(
                    applicationContext,
                    "Please enter your message first",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                when (binding.spinnerEncryptionType.selectedItem) {
                    "Caesar" ->
                        text = addMessageViewModel.encryptCaesar(
                            binding.plainText.text.toString(),
                            binding.caesarKey.text.toString().toInt()
                        )
                    "AES" ->
                        text = addMessageViewModel.encryptAES(
                            binding.plainText.text.toString()
                        )
                    "RSA" ->
                        text = addMessageViewModel.encryptRSA(
                            binding.plainText.text.toString()
                        )
                }
                key = addMessageViewModel.key
                val message = com.snipertech.cryptobud.db.entities.Message(
                    text,
                    key,
                    addMessageViewModel.type,
                    addMessageViewModel.algo
                )
                addMessageViewModel.createMessage(message)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(TEXT_1, text)
                intent.putExtra(TEXT_2, key)
                startActivity(intent)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(){
        //decryption button
        binding.decrypt.setOnClickListener {
            if(binding.plainText.text.toString() == ""){
                Toast.makeText(
                    applicationContext,
                    "Please enter your message first",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                when (binding.spinnerEncryptionType.selectedItem) {
                    "Caesar" ->
                        text = addMessageViewModel.decryptCaesar(
                            binding.plainText.text.toString(),
                            binding.caesarKey.text.toString().toInt()
                        )
                    "AES" ->
                        text = addMessageViewModel.decryptAES(
                            binding.plainText.text.toString(),
                            SecretKeySpec(
                                Base64.getDecoder().decode(binding.caesarKey.text.toString()),
                                0,
                                Base64.getDecoder().decode(binding.caesarKey.text.toString()).size,
                                "AES"
                            )
                        )
                    "RSA" ->
                        text = addMessageViewModel.decryptRSA(
                            binding.plainText.text.toString(),
                            KeyFactory.getInstance("RSA").generatePrivate(
                                PKCS8EncodedKeySpec(
                                    Base64.getDecoder().decode(binding.caesarKey.text.toString())
                                )
                            )
                        )
                }
                key = addMessageViewModel.key
                val message = com.snipertech.cryptobud.db.entities.Message(
                    text,
                    key,
                    addMessageViewModel.type,
                    addMessageViewModel.algo
                )
                addMessageViewModel.createMessage(message)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(TEXT_1, text)
                intent.putExtra(TEXT_2, key)
                startActivity(intent)
            }
        }
    }
}
