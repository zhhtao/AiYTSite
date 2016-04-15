package com.yiqiao.stockpile;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;

public class ChangeInfoActivity extends BaseActivity{
	
	 EditText stockEditText, pricEditText;
	 int stockChanged;
	public double priceChanged;
	private Button oKButton, cancleButton;
	private GoodsChild goodsChild;
	private int position;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_classify_level2_change_info);
		setTitle("�޸���Ϣ");
		mContext = this;
		
		//��ȡ��Ҫ�޸ĵ���Ʒʵ��
		goodsChild = (GoodsChild) getIntent().getSerializableExtra("goodsChild");
		position = getIntent().getIntExtra("goodsChildPosition", 0);
		System.out.println("goodsChildPosition " + position);
		initView();
		
	}
	
	private void initView() {
		stockEditText = (EditText) findViewById(R.id.stock_classify_level2_change_info_stock_et);
		pricEditText = (EditText) findViewById(R.id.stock_classify_level2_change_info_price_et);
		
		oKButton = (Button) findViewById(R.id.stock_classify_level2_change_info_ok_btn);
		cancleButton = (Button) findViewById(R.id.stock_classify_level2_change_info_cancle_btn);
		
		stockEditText.setText(goodsChild.getStock()+"");
		pricEditText.setText(goodsChild.getPrice()+"");
		oKButton.setOnClickListener(new MyOnClickListener());
		cancleButton.setOnClickListener(new MyOnClickListener());
		
		
	}
	
	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			
			//��ȡ�޸ĺ�Ĳ���ֵ
			stockChanged = Integer.parseInt(stockEditText.getText().toString());
			priceChanged = Double.parseDouble(pricEditText.getText().toString());
			
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.stock_classify_level2_change_info_ok_btn:
				
//				System.out.println(stockChanged);
//				System.out.println(priceChanged);
				goodsChild.setPrice(priceChanged);
				goodsChild.setStock(stockChanged);
				
				Intent intent = new Intent();
				//���޸ĺ�Ĳ���ֵ���ݻ�ȥ
				intent.putExtra("data_return_positon", position);
				intent.putExtra("data_return_goodsChild", goodsChild);
				
				setResult(RESULT_OK, intent);
				
				ChangeInfoActivity.this.finish();
				
				break;

			case R.id.stock_classify_level2_change_info_cancle_btn:
				
				ChangeInfoActivity.this.finish();
				break;
				
			default:
				break;
			}
		}
		
	}
}
