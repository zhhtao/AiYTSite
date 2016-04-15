package com.yiqiao.ui;

import com.yiqiao.aiytsite.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class CustomProgress extends Dialog{

	public CustomProgress(Context context) {
		
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomProgress(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
	}
	
	public CustomProgress(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	/**
	 *  
	 * @param message
	 */
    public void setMessage(CharSequence message) {  
        if (message != null && message.length() > 0) {  
            findViewById(R.id.message).setVisibility(View.VISIBLE);  
            TextView txt = (TextView) findViewById(R.id.message);  
            txt.setText(message);  
            txt.invalidate();  
        }  
    }  
  
    public static CustomProgress getInstance(Context context, CharSequence message, boolean cancelable) {
    	CustomProgress dialog = new CustomProgress(context, R.style.Custom_Progress);  
        dialog.setTitle("");  
        dialog.setContentView(R.layout.progress_custom);  
        if (message == null || message.length() == 0) {  
            dialog.findViewById(R.id.message).setVisibility(View.GONE);  
        } else {  
            TextView txt = (TextView) dialog.findViewById(R.id.message);  
            txt.setText(message);  
        }  
        //���öԻ����Ƿ������
        dialog.setCancelable(cancelable);  
        // ���ô���λ��  ����
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
        // ���ñ���͸����
//        dimAmount��0.0f��1.0f֮�䣬0.0f��ȫ�������������ǿɼ��� ��1.0fʱ�򣬱���ȫ����ڰ���
//
//        ���Ҫ�ﵽ����ȫ���䰵��Ч������Ҫ����  dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        �����򣬱�����Ч����
        lp.dimAmount = 0.6f;  
        dialog.getWindow().setAttributes(lp);  
        return dialog;  
    }

    /**
     * 
     * @param context
     * @param message
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static CustomProgress show(Context context, CharSequence message, boolean cancelable, OnCancelListener cancelListener) {  
        CustomProgress dialog = new CustomProgress(context, R.style.Custom_Progress);  
        dialog.setTitle("");  
        dialog.setContentView(R.layout.progress_custom);  
        if (message == null || message.length() == 0) {  
            dialog.findViewById(R.id.message).setVisibility(View.GONE);  
        } else {  
            TextView txt = (TextView) dialog.findViewById(R.id.message);  
            txt.setText(message);  
        }  
        //���öԻ����Ƿ������
        dialog.setCancelable(cancelable);  
        //����ɾ��������¼�   
        dialog.setOnCancelListener(cancelListener);  
        // ���ô���λ��  ����
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;  
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();  
        // ���ñ���͸����
//      dimAmount��0.0f��1.0f֮�䣬0.0f��ȫ�������������ǿɼ��� ��1.0fʱ�򣬱���ȫ����ڰ���
//
//        ���Ҫ�ﵽ����ȫ���䰵��Ч������Ҫ����  dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        ���򣬱�����Ч����
        lp.dimAmount = 0.6f;  
        dialog.getWindow().setAttributes(lp);  
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);  
        dialog.show();  
        return dialog;  
    }
}  


