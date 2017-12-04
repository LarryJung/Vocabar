package com.dot2line.vocabar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class MDialogFragment extends DialogFragment {
  public static final String TAG = MDialogFragment.class.getSimpleName();

    VCBDialogListener listener;

    AlertDialog dialog;
    EditText edtBookName;

    public interface VCBDialogListener {
        void onDialogPositiveClick(DialogFragment fragment, String bookName);
    }

    public MDialogFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (VCBDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement VCBDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_vocabook, null);
        edtBookName = view.findViewById(R.id.edt_book_name);
        edtBookName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dialog.getButton(AlertDialog.BUTTON1).setEnabled(!TextUtils.isEmpty(edtBookName.getText().toString()));
            }
        });
        builder.setView(view)
            .setPositiveButton(R.string.add, (dialog, id) ->
                listener.onDialogPositiveClick(MDialogFragment.this, edtBookName.getText().toString()))
            .setNegativeButton(R.string.cancel, (dialog, id) ->
                dismiss());
        dialog = builder.create();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog.getButton(AlertDialog.BUTTON1).setEnabled(false);
    }
}
