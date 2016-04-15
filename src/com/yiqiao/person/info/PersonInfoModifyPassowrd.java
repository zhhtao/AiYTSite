package com.yiqiao.person.info;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.aiytsite.R;
import com.yiqiao.util.MyConstants;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class PersonInfoModifyPassowrd extends FinalActivity{
	@ViewInject(id=R.id.person_info_modify_password_back_btn) private Button backButton;
	@ViewInject(id=R.id.person_info_modify_password_submit_btn) private Button submitButton;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_old)private EditText oldEditText;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_new)private EditText newEditText;
	@ViewInject(id=R.id.person_info_modify_password_edit_pw_ack)private EditText newAgainEditText;
	
	private Context mContext;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_modify_password);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
		submitButton.setOnClickListener(new MyOnClickListener());
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.person_info_modify_password_back_btn:
				back();
				break;
			case R.id.person_info_modify_password_submit_btn:
				String oldPassword = oldEditText.getText().toString();
				String newPassword = newEditText.getText().toString();
				String newAgainPassword = newAgainEditText.getText().toString();
				
				if (!oldPassword.equals(MyConstants.currentUserPassword)) {
					new AlertDialog.Builder(mContext)
					.setTitle("��ʾ")
					.setMessage("ԭ�����������")
					.setPositiveButton("ȷ��", null)
					.create().show();
				} else if (!newPassword.equals(newAgainPassword)) {
					new AlertDialog.Builder(mContext)
					.setTitle("��ʾ")
					.setMessage("2���������벻һ�£�")
					.setPositiveButton("ȷ��", null)
					.create().show();
				} else if (newPassword.length() < 8) {
					new AlertDialog.Builder(mContext)
					.setTitle("��ʾ")
					.setMessage("���볤�Ȳ���С��8λ��")
					.setPositiveButton("ȷ��", null)
					.create().show();
				} else {
					//�ϴ���������
					
				}
				break;

			default:
				break;
			}
		}
		
	}
	//���ؼ�����
	private void back() {
		super.onBackPressed();
	}
}
