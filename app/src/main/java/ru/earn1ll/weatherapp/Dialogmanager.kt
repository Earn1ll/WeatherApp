package ru.earn1ll.weatherapp

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText

object Dialogmanager {
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

    fun searchByNameDialog(context: Context,listener:Listener){
        val builder =AlertDialog.Builder(context)
        val diaog = builder.create()
        val edName = EditText(context)
        builder.setView(edName)
        diaog.setTitle("City name:")
        diaog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok"){_,_ ->
            listener.onClick(edName.text.toString())
            diaog.dismiss()
        }
        diaog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel"){_,_ ->
            diaog.dismiss()
        }
        diaog.show()
    }
    interface Listener{
        fun onClick(name:String?)
    }
}