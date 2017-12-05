package com.dot2line.vocabar

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText

class MDialogFragment : DialogFragment() {

  lateinit var dialog: AlertDialog
  lateinit var edtBookName: EditText

  lateinit var listener: VCBDialogListener

  interface VCBDialogListener {
    fun onDialogPositiveClick(fragment: DialogFragment, bookName: String)
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    try {
      listener = context as VCBDialogListener
    } catch (e: ClassCastException) {
      throw ClassCastException(context!!.toString() + " must implement VCBDialogListener")
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val view = activity.layoutInflater.inflate(R.layout.dialog_add_vocabook, null)
    edtBookName = view.findViewById(R.id.edt_book_name)
    edtBookName.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
      }

      override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
      }

      override fun afterTextChanged(editable: Editable) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !TextUtils.isEmpty(edtBookName.text.toString())
      }
    })
    dialog = AlertDialog.Builder(activity).setView(view)
        .setPositiveButton(R.string.add) { dialog, id ->
          listener.onDialogPositiveClick(this@MDialogFragment, edtBookName.text.toString()) }
        .setNegativeButton(R.string.cancel) { dialog, id -> dismiss() }
        .create()
    return dialog
  }

  override fun onStart() {
    super.onStart()
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
  }

  companion object {
    val TAG = MDialogFragment::class.java.simpleName!!
  }
}
