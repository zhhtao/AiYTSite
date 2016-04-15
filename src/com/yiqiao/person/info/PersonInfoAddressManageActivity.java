package com.yiqiao.person.info;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiqiao.aiytsite.R;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class PersonInfoAddressManageActivity extends FinalActivity{
	@ViewInject(id=R.id.person_info_address_back_btn) private Button backButton;
	@ViewInject(id=R.id.person_info_address_old_textView)private TextView oldAddress;
	@ViewInject(id=R.id.person_info_address_new_edittext)private EditText newAddress;
	@ViewInject(id=R.id.person_info_address_submit_btn)private Button submitButton;
	
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_info_address_manage);
		mContext = this;
		
		backButton.setOnClickListener(new MyOnClickListener());
		submitButton.setOnClickListener(new MyOnClickListener());
	}
	
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.person_info_address_back_btn:
				back();
				break;
			case R.id.person_info_address_submit_btn://�ύ�����µ�ַ
				if (newAddress.getText().toString().equals("")) {
					new AlertDialog.Builder(mContext)
					.setTitle("��ʾ")
					.setMessage("��������Ч��ַ��")
					.setPositiveButton("ȷ��", null)
					.create().show();
				} else {
					String addr = newAddress.getText().toString();
					oldAddress.setText(addr);
					
					//�ϴ�����������
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
