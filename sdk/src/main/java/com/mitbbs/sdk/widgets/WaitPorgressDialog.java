package com.mitbbs.sdk.widgets;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * data:${DATA}.
 *
 * @author:jc
 * 等待提示dialog
 */
public class WaitPorgressDialog extends ProgressDialog {
    public WaitPorgressDialog(Context context) {
        this(context,0);
    }
    public WaitPorgressDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }
}
