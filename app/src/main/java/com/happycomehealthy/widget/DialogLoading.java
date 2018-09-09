package com.happycomehealthy.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.happycomehealthy.R;

/**
 * Created by shixinshan on 2018/5/2.
 */

public class DialogLoading extends Dialog {
    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_loading_layout);
        setCanceledOnTouchOutside(false);
        loadingLabel = (TextView) findViewById(R.id.loading_text);
    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }
}
