package com.snipertech.cryptobud.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.snipertech.cryptobud.db.entities.Message
import com.snipertech.cryptobud.repository.MessageRepository
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class AddMessageViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        private const val ALPHABET = " ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    private val repository = MessageRepository(application)
    private lateinit var cipherText: StringBuilder
    private lateinit var plainText: StringBuilder
    var key = ""
    var type = ""
    var algo = ""

    fun createMessage(message: Message) {
        repository.insert(message)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun encryptCaesar(message: String, cKey: Int): String {
        cipherText = StringBuilder()
        key = cKey.toString()
        type = "Encryption"
        algo = "Caesar"
        for (element in message.toUpperCase(Locale.ROOT)) {
            val character = element
            val charIndex = ALPHABET.indexOf(character)
            val encryptedIndex = Math.floorMod(charIndex + cKey, ALPHABET.length)
            cipherText.append(ALPHABET[encryptedIndex])
        }
        return cipherText.toString()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun decryptCaesar(message: String, dKey: Int): String {
        type = "Decryption"
        algo = "Caesar"
        key = dKey.toString()
        plainText = StringBuilder()
        for (element in message) {
            val charIndex = ALPHABET.indexOf(element)
            val decryptedIndex = Math.floorMod(charIndex - dKey, ALPHABET.length)
            plainText.append(ALPHABET[decryptedIndex])
        }
        return plainText.toString()
    }

    //...............................................................................
    @SuppressLint("GetInstance")
    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptAES(message: String): String {
        cipherText = StringBuilder()
        val kryptoKey: SecretKey = KeyGenerator.getInstance("AES").generateKey()
        type = "Encryption"
        algo = "AES"
        key = Base64.getEncoder().encodeToString(kryptoKey.encoded)
        //Creating a Cipher
        val cipher: Cipher = Cipher.getInstance("AES")
        //Initializing a Cipher
        cipher.init(Cipher.ENCRYPT_MODE, kryptoKey)
        //encrypting
        val text: ByteArray = cipher.doFinal(message.toByteArray())
        return Base64.getEncoder().encodeToString(text)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("GetInstance")
    fun decryptAES(message: String, dKey: SecretKey): String {
        type = "Decryption"
        algo = "AES"
        key = Base64.getEncoder().encodeToString(dKey.encoded)
        plainText = StringBuilder()
        //Creating a Cipher
        val cipher = Cipher.getInstance("AES")
        //Initializing a Cipher
        cipher.init(Cipher.DECRYPT_MODE, dKey)
        //decrypting
        val decodedText: ByteArray = Base64.getDecoder().decode(message.toByteArray())
        val plainText = cipher.doFinal(decodedText)
        return String(plainText)
    }

    //...............................................................................
    @SuppressLint("GetInstance")
    @RequiresApi(Build.VERSION_CODES.O)
    fun encryptRSA(message: String): String {
        cipherText = StringBuilder()
        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(1024)

        // Generate the KeyPair
        val keyPair: KeyPair = keyPairGenerator.generateKeyPair()

        // Get the public and private key
        val publicKey: PublicKey = keyPair.public
        val privateKey: PrivateKey = keyPair.private
        type = "Encryption"
        algo = "RSA"
        key = Base64.getEncoder().encodeToString(privateKey.encoded)

        //Creating a Cipher
        val cipher: Cipher = Cipher.getInstance("RSA")
        //Initializing a Cipher
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        //encrypting
        val text: ByteArray = cipher.doFinal(message.toByteArray())
        return Base64.getEncoder().encodeToString(text)
    }

    @SuppressLint("GetInstance")
    @RequiresApi(Build.VERSION_CODES.O)
    fun decryptRSA(message: String, privateKey: PrivateKey): String {
        type = "Decryption"
        algo = "RSA"
        key = Base64.getEncoder().encodeToString(privateKey.encoded)

        cipherText = StringBuilder()
        //Creating a Cipher
        val cipher: Cipher = Cipher.getInstance("RSA")
        //Initializing a Cipher
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        //decrypting
        val decodedText: ByteArray = Base64.getDecoder().decode(message.toByteArray())
        val plainText = cipher.doFinal(decodedText)
        return String(plainText)
    }

}