package com.lujiahao.l_library.view;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * ProgressDialog工厂类  用于创建各种类型的dialog
 * Created by lujiahao
 * Created at 2016/5/18 10:46
 */
public class ProgressDialogFactory {
    private Context context;
    private static ProgressDialogFactory INSTANCE;
    private ProgressDialogFactory(Context context) {
        this.context = context;
    }
    public static synchronized ProgressDialogFactory getInstance(Context context){
        if (INSTANCE == null) {
            INSTANCE = new ProgressDialogFactory(context);
        }
        return INSTANCE;
    }

    protected ProgressDialog progressDialog;
    /**
     * 显示正在加载的进度条
     * @param msg	进度条提示信息
     */
    public ProgressDialog showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        progressDialog.show();
//        View view = View.inflate(this, R.layout.view_progressdialog, null);
//        TextView tv_progress_name = (TextView) view.findViewById(R.id.tv_progress_name);
//        tv_progress_name.setText(msg);
//        myCustomProgressDialog.setContentView(view);
        return progressDialog;
    }
    public ProgressDialog showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        progressDialog.show();
//        View view = View.inflate(this, R.layout.view_progressdialog, null);
//        TextView tv_progress_name = (TextView) view.findViewById(R.id.tv_progress_name);
//        tv_progress_name.setText(msg);
//        myCustomProgressDialog.setContentView(view);
        return progressDialog;
    }
    /**
     * 取消对话框显示
     */
    public void disMissDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
