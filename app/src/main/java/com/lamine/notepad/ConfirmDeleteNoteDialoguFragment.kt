package com.lamine.notepad

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 *Created by Lamine MESSACI on 10/08/2020.
 */
class ConfirmDeleteNoteDialoguFragment(val noteTitle: String = ""): DialogFragment() {

    interface ConfirmDeleteDialogListner {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }
    var listner: ConfirmDeleteDialogListner? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Ête vous sûr de vouloir supprimer la note ?\" $noteTitle\"?")
            .setPositiveButton("Supprimer") { dialog, id -> listner?.onDialogPositiveClick() }
            .setNegativeButton("Annuler"){dialog, id -> listner?.onDialogNegativeClick()}

        return builder.create()
    }
}