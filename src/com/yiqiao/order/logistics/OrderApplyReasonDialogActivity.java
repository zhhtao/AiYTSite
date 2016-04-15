package com.yiqiao.order.logistics;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiqiao.aiytsite.R;

//ʹ��δ������ ȡ������������ԭ��Ĳ���
public class OrderApplyReasonDialogActivity extends FinalActivity{
	@ViewInject(id=R.id.order_wei_fa_huo_canle_order_ok_btn) private Button okButton;
	@ViewInject(id=R.id.order_wei_fa_huo_canle_order_cancle_btn) private Button cancleButton;
	@ViewInject(id=R.id.order_wei_fa_huo_canle_order_input_reason_textView) private TextView inputTextView;
	@ViewInject(id=R.id.order_wei_fa_huo_canle_order_input_reason) private EditText cancleReasonEditText;
	private Context mContext;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_wei_fa_huo_canle_order_dialog_activity);
		
		if (getIntent().getStringExtra("button").equals("agree")) {
			setTitle("ͬ��");
		} else if (getIntent().getStringExtra("button").equals("notagree")) {
			setTitle("��ͬ��");
		}
		
		inputTextView.setText("������ԭ��");
		position = getIntent().getIntExtra("position", -1);
		
		okButton.setOnClickListener(new MyOnClickListener());
		cancleButton.setOnClickListener(new MyOnClickListener());
	}
	
	private class MyOnClickListener implements OnClickListener {

		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_wei_fa_huo_canle_order_ok_btn:
				Intent intent = new Intent();
				String cancleReasonString = cancleReasonEditText.getText().toString();
				intent.putExtra("selected_positon", position);
				intent.putExtra("cancle_reason", cancleReasonString);
				setResult(RESULT_OK, intent);
				finish();
				break;
			case R.id.order_wei_fa_huo_canle_order_cancle_btn:
				setResult(RESULT_CANCELED);
				finish();
				break;

			default:
				break;
			}
		}
		
	}
}
