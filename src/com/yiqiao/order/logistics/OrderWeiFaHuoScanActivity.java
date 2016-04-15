package com.yiqiao.order.logistics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import com.yiqiao.adapter.OrderWeiFaHuoScanAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderInvoiceInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.ui.CustomProgress;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

public class OrderWeiFaHuoScanActivity extends FinalActivity{
	@ViewInject(id=R.id.order_weifahuo_scan_progress_bar_layout)private RelativeLayout progressBarLayout;
	@ViewInject(id=R.id.order_weifahuo_scan_show_info_layout)private LinearLayout showInfoLayout;
	@ViewInject(id=R.id.order_weifahuo_scan_title_back)private ImageView backImageView; 
	@ViewInject(id=R.id.order_weifahuo_scan_listview)private ListView listView;
	
	private static final int SHOW_INFO_LAYOUT = 1;
	private static final int SHOW_PROGRESS_BAR_LAYOUT = 2;
	private static final int REFRESH_LISTVIEW = 3;
	private Context mContext;
	private List<OrderTotalInfo> orderTotalInfosList = new ArrayList<OrderTotalInfo>();
	private OrderWeiFaHuoScanAdapter adapter;//�������������
	
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
		setContentView(R.layout.order_wei_fa_huo_scan_layout);
		mContext = this;
		
		initView();
	}
	
	@Override
	protected void  onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1)//��ȡ��������������
		{
			if (resultCode == RESULT_OK) {//�ж���ȡ��
				int position = data.getIntExtra("selected_positon", -1);
				String cancleReason = data.getStringExtra("cancle_reason");
				
				//ȡ����������� ����״̬  ����Listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_YIQUXIAO);//����״̬Ϊ��ȡ������
				//�ϴ�����������
				
				//����UI ɾ����ȡ������
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
			}
		} else if (requestCode == 2) { //�ӷ�����ť ����
			if (resultCode == RESULT_OK) {
				int position = data.getIntExtra("position", -1);
				OrderInvoiceInfo orderInvoiceInfo = (OrderInvoiceInfo) data.getSerializableExtra("orderInvoiceInfo");
				
				final CustomProgress customProgress = CustomProgress.show(mContext, "", false, null);
				//�ϴ�����������

				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						customProgress.dismiss();
					}
				}, 1500);
				//�ϴ��ɹ�
				
				//������ ����״̬ ����listview
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
				orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_YIFAHUO);//����״̬Ϊ�ѷ�������
				
				System.out.println(orderInvoiceInfo.getLogisticsCompany()+orderInvoiceInfo.getDeliverTime()+orderInvoiceInfo.getLogisticsSerial());
				//����UI ɾ���ѷ�������
				orderTotalInfosList.remove(position);
				mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
				
				
				//���ɹ�  ������ʾ
				
				
			}
		}
	}
	private void initView() {
		//��ʾ������
		progressBarLayout.setVisibility(View.VISIBLE);
		showInfoLayout.setVisibility(View.INVISIBLE);
		backImageView.setOnClickListener(new MyOnClickListener());
		
		adapter = new OrderWeiFaHuoScanAdapter(mContext, orderTotalInfosList);
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
			orderTotalInfo.setOrderState(MyConstants.ORDER_STATE_WEIFAHUO);
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
			case R.id.order_weifahuo_scan_title_back:
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
