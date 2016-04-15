package com.yiqiao.order.logistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiqiao.adapter.OrderQuXiaoShenQingScanAdapter;
import com.yiqiao.adapter.OrderTuiHuoShenQingScanAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.util.MyConstants;

//ʹ�õĺܶණ���� ȡ��������ͬ
public class OrderTuiHuoShengQingScanActivity extends FinalActivity{
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_progress_bar_layout)private RelativeLayout progressBarLayout;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_show_info_layout)private LinearLayout showInfoLayout;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_title_back)private ImageView backImageView; 
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_listview)private ListView listView;
	@ViewInject(id=R.id.order_quxiao_shenqing_scan_title) private TextView titleTextView;
	
	private static final int SHOW_INFO_LAYOUT = 1;
	private static final int SHOW_PROGRESS_BAR_LAYOUT = 2;
	private static final int REFRESH_LISTVIEW = 3;
	private Context mContext;
	private List<OrderTotalInfo> orderTotalInfosList = new ArrayList<OrderTotalInfo>();
	private OrderTuiHuoShenQingScanAdapter adapter;//�������������
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_INFO_LAYOUT:
				progressBarLayout.setVisibility(View.GONE);
				showInfoLayout.setVisibility(View.VISIBLE);
				
				break;
				
			case SHOW_PROGRESS_BAR_LAYOUT:
				progressBarLayout.setVisibility(View.VISIBLE);
				showInfoLayout.setVisibility(View.INVISIBLE);
				
				break;
				
			case REFRESH_LISTVIEW:
				adapter.notifyDataSetChanged();
				break;

			default:
				break;
			}
			
	    }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_quxiao_shenqing_scan_layout);
		titleTextView.setText("�˻�����");
		mContext = this;
		
		initView();
	}
	
	@Override
	protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1)//ͬ���˻�
		{
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("selected_positon", -1);
				String cancleReason = data.getStringExtra("cancle_reason");
				System.out.println(cancleReason);
				
				//�˻������ ����״̬  ����Listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_TUIHUO_ZHONG);//����״̬Ϊ�˻��ж���
				//�ϴ�����������
				
				//����UI ɾ����ȡ������
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
			}
		} else if (requestCode == 2) { //��ͬ���˻�
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("selected_positon", -1);
				String cancleReason = data.getStringExtra("cancle_reason");
				System.out.println(cancleReason);
				
				//��ͬ���˻������ ����״̬  ����Listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_WANCHENG);//����״̬Ϊ����ɶ���
				//�ϴ�����������
				
				//����UI 
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
				
			}
		}
	}
	private void initView() {
		//��ʾ������
		progressBarLayout.setVisibility(View.VISIBLE);
		showInfoLayout.setVisibility(View.INVISIBLE);
		backImageView.setOnClickListener(new MyOnClickListener());
		
		adapter = new OrderTuiHuoShenQingScanAdapter(mContext, orderTotalInfosList);
		listView.setAdapter(adapter);
		
		qureyDataFromServer();
		mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
	}
	
	//�ӷ�������ȡ����
	private void qureyDataFromServer() {
		
		//ģ������ʱ��
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.sendEmptyMessage(SHOW_INFO_LAYOUT);
			}
		}, 1000);
		
		for (int i=0; i<10; i++) {
			
			OrderTotalInfo orderTotalInfo = new OrderTotalInfo();
			//������Ϣ
			orderTotalInfo.setOrderSeq("59008028004"+i);
			orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_TUIHUO_SHENAQINF);
			orderTotalInfo.setRealPayMoney(28.8+i*10);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = sdf.format(new Date());
			
			orderTotalInfo.setOrderTime(time);
			
			//��Ʒ��Ϣ���
			List<OrderGoodsInfo> orderGoodsInfosList = new ArrayList<OrderGoodsInfo>();
			for (int j = 0; j < 3; j++) {
				OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
				
				orderGoodsInfo.setGoodsName("��ĸ��"+j);
				orderGoodsInfo.setType(j+5+"��/ֻ");
				orderGoodsInfo.setAttribute("����"+j);
				orderGoodsInfo.setNums(100+j*8);
				orderGoodsInfo.setPrice(15.6+j*10);
				orderGoodsInfosList.add(orderGoodsInfo);
			}
			orderTotalInfo.setGoodsInfosList(orderGoodsInfosList);
			
			
			OrderInvoiceInfo orderInvoiceInfo = new OrderInvoiceInfo();
			orderInvoiceInfo.setConsumerName("������");
			orderInvoiceInfo.setTel("15566889966");
			orderInvoiceInfo.setAddress("�Ĵ�ʡ�ɶ��ж���·��������5��1��Ԫ2301");
			orderTotalInfo.setOrderInvoiceInfo(orderInvoiceInfo);
			
			orderTotalInfosList.add(orderTotalInfo);
		}
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_quxiao_shenqing_scan_title_back:
				back();
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
