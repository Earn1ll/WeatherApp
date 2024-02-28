package ru.earn1ll.weatherapp

import android.app.AlertDialog
import android.content.Context

object Dialogmanager {
    fun locationSettingsDialog(context: Context,listener:Listener){
        val builder =AlertDialog.Builder(context)
        val diaog = builder.create()
        diaog.setTitle("Enable location?")
        diaog.setMessage("Location disabled,do you want enable location?")
        diaog.setButton(AlertDialog.BUTTON_POSITIVE,"Ok"){_,_ ->
            listener.onClick()
            diaog.dismiss()
        }
        diaog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel"){_,_ ->
            diaog.dismiss()
        }
        diaog.show()
    }
    interface Listener{
        fun onClick()
    }
}