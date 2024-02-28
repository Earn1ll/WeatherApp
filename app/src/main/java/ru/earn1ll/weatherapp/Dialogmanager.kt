package ru.earn1ll.weatherapp

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object DialogManager {
    fun locationSettingsDialog(context: Context,listener:Listener){
        val builder =AlertDialog.Builder(context)
        val diaog = builder.create()
        diaog.setTitle("Enable location?")
        diaog.setMessage("Location disabled,do you want enable location?")
        diaog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok"){_,_ ->
            listener.onClick(null)
            diaog.dismiss()
        }
        diaog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel"){_,_ ->
            diaog.dismiss()
        }
        diaog.show()
    }

    fun searchByNameDialog(context: Context, listener: Listener){
        val builder = AlertDialog.Builder(context)
        val edName = EditText(context)
        builder.setView(edName)
        val dialog = builder.create()
        dialog.setTitle("City name:")
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK"){ _,_ ->
            listener.onClick(edName.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel"){ _,_ ->
            dialog.dismiss()
        }
        dialog.show()
    }
    interface Listener{
        fun onClick(name:String?)
    }
}