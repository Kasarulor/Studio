package com.kyli.kui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.kyli.kui.view.KUILoadingView;

public class KUiLoadingDialogUtils {

    public static Dialog create(Context context) {
        KUiZeroDimDialog kUiZeroDimDialog = new KUiZeroDimDialog(context);
        KUILoadingView kuiLoadingView = new KUILoadingView(context);
        kuiLoadingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        kUiZeroDimDialog.setContentView(kuiLoadingView);
        return kUiZeroDimDialog;
    }
}
