package com.cd.jyf.studio;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.kyli.kui.dialog.KUiLoadingDialogUtils;
import com.kyli.kui.dialog.KUiZeroDimDialog;
import com.kyli.kui.dialog.KuiLoadingDialog;
import com.kyli.kui.view.KUILoadingView;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 2000);
            }
        });
        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        init();
    }

    private void init() {
        dialog = new KuiLoadingDialog(this);

    }
}
