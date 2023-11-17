package br.ufrn.imd.imd_market.utils;

import android.app.AlertDialog;
import android.content.Context;

public class MessageDisplay {
    public static void showMessage(String title, String message, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.show();
    }
}
