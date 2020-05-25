package com.snipertech.cryptobud.view.ui

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.method.ScrollingMovementMethod
import android.view.Window
import android.widget.Toast
import com.snipertech.cryptobud.R
import kotlinx.android.synthetic.main.dialog_message.*


object MessageDialog {
    fun showDialog(
        activity: Activity,
        message: String,
        type: String,
        algo: String,
        key: String
    ) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_message)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val messageText = dialog.text
        val messageType = dialog.text_type
        val messageAlgo = dialog.text_algo
        val messageKey = dialog.text_key
        val dialogButton = dialog.btn_dialog

        //to make it scrollable with copy to clipboard functionality
        dialog.text.apply {
            movementMethod = ScrollingMovementMethod()
            setOnClickListener{
                val cm: ClipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Message", text)
                cm.setPrimaryClip(clip)
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }

        //to make it scrollable with copy to clipboard functionality
        dialog.text_key.apply {
            movementMethod = ScrollingMovementMethod()
            setOnClickListener{
                val cm: ClipboardManager =
                    context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Key", text)
                cm.setPrimaryClip(clip)
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }


        messageText.text = message
        messageType.text = type
        messageAlgo.text = algo
        messageKey.text = key

        dialogButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}