package com.yiqiao.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.aiytsite.R;

public class RegisterActivity extends BaseActivity{
	private EditText registerEmail,registerUserName,registerPassword,registerPasswordTwo;
	private Button registerCommitButton;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login_register);
		
		mContext = this;
		initView();
		
	}
	
	void initView() {
		
		registerEmail = (EditText) findViewById(R.id.et_register_email);
		registerUserName = (EditText) findViewById(R.id.et_register_username);
		registerPassword = (EditText) findViewById(R.id.et_register_password);
		registerPasswordTwo = (EditText) findViewById(R.id.et_register_password_two);
		
		registerCommitButton = (Button) findViewById(R.id.btn_reister_commit);
		registerCommitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email = registerEmail.getText().toString();
				String userName = registerUserName.getText().toString();
				String password = registerPassword.getText().toString();
				
				if (!isEmail(email)) {
					new AlertDialog.Builder(mContext)
					.setIcon(
							getResources().getDrawable(
									com.yiqiao.aiytsite.R.drawable.login_error_icon))
					.setTitle("��ʾ").setMessage("��������Ч�������ַ!")
					.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					})
					.create().show();
				} else if (!registerPassword.getText().toString().equals(registerPasswordTwo.getText().toString())) {
					new AlertDialog.Builder(mContext)
					.setIcon(
							getResources().getDrawable(
									com.yiqiao.aiytsite.R.drawable.login_error_icon))
					.setTitle("��ʾ").setMessage("2���������벻һ�£�")
					.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					})
					.create().show();
				} else if (!isUserNameValid(userName)) {//�û�����Ч
					new AlertDialog.Builder(mContext)
					.setIcon(
							getResources().getDrawable(
									com.yiqiao.aiytsite.R.drawable.login_error_icon))
					.setTitle("��ʾ").setMessage("���û����Ѵ��ڣ������������û�����")
					.setPositiveButton(com.yiqiao.aiytsite.R.string.login_alert_dialog_ok, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					})
					.create().show();
				} else {//������֤��ȷ���ύ����
					final ProgressDialog progressDialog = new ProgressDialog(mContext);
					progressDialog.setCancelable(true);
					progressDialog.setTitle("��ʾ");
					progressDialog.setMessage("����Ŭ��ע���С���");
					progressDialog.show();
					
					//�ύ����
					
					
					//�ύ���
					//ģ��ȴ�					
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							progressDialog.dismiss();
							Intent registOK = new Intent(mContext, RegisterSucceed.class);
							startActivity(registOK);
						}
					}, 2000);
					
				}
				
				
			}
		});
	}
	
    // �жϸ�ʽ�Ƿ�Ϊemail
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    //�ж��û����Ƿ����
    public boolean isUserNameValid(String userName) {
    	
    	
    	return true;
    }

}
