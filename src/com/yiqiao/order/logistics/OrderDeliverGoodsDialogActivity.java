package com.yiqiao.order.logistics;

import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.ui.DateTimePickDialogUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class OrderDeliverGoodsDialogActivity extends FinalActivity{
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_ok_btn) private Button okButton;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_cancle_btn) private Button cancleButton;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_time) private EditText timeEditText;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_seq) private EditText seqEditText;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_company) private Spinner companySpinner;
	@ViewInject(id=R.id.order_wei_fa_huo_deliver_goods_input_company_other) private EditText otherCompanyEditText;
	private Context mContext;
	private static final String[] expressCompany = {"˳����","��ͨ���","Բͨ���","��ͨ���","��ͨ����","�ϴ���","������","EMS","լ����","������ݹ�˾"};
	private ArrayAdapter<String> adapter;
	private String selectedCompany;
	private String expressSeq;
	private String deliverTime;
	private int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_wei_fa_huo_deliver_goods_dialog_activity);
		mContext = this;
		
		setTitle("���뷢����Ϣ");
		position = getIntent().getIntExtra("position", -1);
		
		adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, expressCompany);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		companySpinner.setAdapter(adapter);
		
		okButton.setOnClickListener(new MyOnClickListener());
		cancleButton.setOnClickListener(new MyOnClickListener());
		//������˾ѡ��
		companySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				selectedCompany = expressCompany[position];
//				System.out.println(selectedCompany);
				if ("������ݹ�˾".equals(selectedCompany)) {
					otherCompanyEditText.setVisibility(View.VISIBLE);
				} else {
					otherCompanyEditText.setVisibility(View.GONE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
		
		//����ʱ��ȷ��
		timeEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String initDateTime="2015��6��6�� 11:11";
				DateTimePickDialogUtil dateTimePickDialogUtil = new DateTimePickDialogUtil(OrderDeliverGoodsDialogActivity.this, initDateTime);
				dateTimePickDialogUtil.dateTimePicKDialog(timeEditText);
			}
		});
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_wei_fa_huo_deliver_goods_ok_btn:
				
				//����������Ϣ
				OrderInvoiceInfo orderInvoiceInfo = new OrderInvoiceInfo();
				if ("������ݹ�˾".equals(selectedCompany)) {
					selectedCompany = otherCompanyEditText.getText().toString();
				}
				expressSeq = seqEditText.getText().toString();//����
				String timeString = timeEditText.getText().toString();//����ʱ��
				
				if (expressSeq.equals("") || timeString.equals("")) {
					new AlertDialog.Builder(mContext)
					.setTitle("��ʾ").setMessage("��������Ч��Ϣ��")
					.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						}
					})
					.create().show();
				} else {
					
					orderInvoiceInfo.setDeliverTime(timeString);
					orderInvoiceInfo.setLogisticsCompany(selectedCompany);
					orderInvoiceInfo.setLogisticsSerial(expressSeq);
					
					
					
					Intent intent = new Intent();
					intent.putExtra("position", position);
					intent.putExtra("orderInvoiceInfo", orderInvoiceInfo);
					setResult(RESULT_OK, intent);
					
					finish();
				}
				
				break;
				
			case R.id.order_wei_fa_huo_deliver_goods_cancle_btn:
				
				setResult(RESULT_CANCELED);
				finish();
				break;

			default:
				break;
			}
		}
		
	}

}
