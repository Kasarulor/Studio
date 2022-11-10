package com.kyli.kui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.kyli.kui.view.KUILoadingView;

public class KuiLoadingDialog extends KUiZeroDimDialog {
    private KUILoadingView kuiLoadingView;

    public KuiLoadingDialog(@NonNull Context context) {
        super(context);
    }

    public KuiLoadingDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLoadingView());

    }

    @Override
    public void show() {
        super.show();
        kuiLoadingView.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        kuiLoadingView.stop();
    }


    private View getLoadingView() {
        kuiLoadingView = new KUILoadingView(getContext());

        kuiLoadingView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return kuiLoadingView;
    }


}
