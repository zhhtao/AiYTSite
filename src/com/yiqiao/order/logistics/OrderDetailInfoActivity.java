package com.yiqiao.order.logistics;

import java.util.List;

import net.tsz.afinal.FinalActivity;
import net.tsz.afinal.annotation.view.ViewInject;

import com.yiqiao.adapter.OrderGoodsInfoListAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderGoodsInfo;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.ui.ListViewForScrollView;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyUtility;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class OrderDetailInfoActivity extends FinalActivity{
	@ViewInject(id=R.id.order_detail_info_main_scrollview) private ScrollView mainScrollView;
	@ViewInject(id=R.id.order_detail_info__title_back) private ImageView backImageView;
	@ViewInject(id=R.id.order_detail_info_seq) private TextView seqTextView;
	@ViewInject(id=R.id.order_detail_info_order_state) private TextView orderStateTextView;
	@ViewInject(id=R.id.order_detail_info_create_time) private TextView creatTimeTextView;
	@ViewInject(id=R.id.order_detail_info_goods_listview) private ListView goodsListView;
	@ViewInject(id=R.id.order_detail_info_consignee_name) private TextView consigneeNameTextView;
	@ViewInject(id=R.id.order_detail_info_consignee_tel) private TextView consigneeTelTextView;
	@ViewInject(id=R.id.order_detail_info_consignee_address) private TextView consigneeAddressTextView;
	
	@ViewInject(id=R.id.order_detail_info_logistics_deliver_time) private TextView deliverTimeTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_company) private TextView logicsticsCompanyTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_serial) private TextView logicsticsSerialTextView;
	@ViewInject(id=R.id.order_detail_info_logistics_receive_time_layout) private LinearLayout receiveTimeLayout;
	@ViewInject(id=R.id.order_detail_info_logistics_receive_time) private TextView receiveTimeTextView;
	
	
	@ViewInject(id=R.id.order_detail_info_logicstics_info_layout) private LinearLayout logisticsLayout;
	@ViewInject(id=R.id.order_detail_info_consignee_layout) private LinearLayout receiverAndLogisticsLayout;
	@ViewInject(id=R.id.order_detail_info_cancle_reason_layout) private LinearLayout cancleReasonLayout;
	@ViewInject(id=R.id.order_detail_info_cancle_reason) private TextView cancleReasonTextView;
	
	@ViewInject(id=R.id.order_detail_info_return_goods_layout) private LinearLayout returnGoodsLayout;
	
	
	private Context mContext;
	private OrderTotalInfo orderTotalInfo;
	private List<OrderGoodsInfo> orderGoodsInfosList;
	private OrderGoodsInfoListAdapter orderGoodsInfoListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_detail_info);
		mContext = this;
		
		orderTotalInfo = (OrderTotalInfo) getIntent().getSerializableExtra("orderTotalInfo");
		System.out.println(orderTotalInfo.getOrderSeq());
		initView();
	}
	
	private void initView() {
		backImageView.setOnClickListener(new MyOnClickListener());
		
		//������һ����ݹ����Ķ�����Ϣ ����UI
		//����״̬�趨
		switch (orderTotalInfo.getOrderState()) {
		case MyConstants.ORDER_STATE_WEIFAHUO:
			orderStateTextView.setText("����״̬��δ����");
			logisticsLayout.setVisibility(View.GONE);//����������Ϣ
			
			break;
		case MyConstants.ORDER_STATE_YIFAHUO:
			orderStateTextView.setText("����״̬���ѷ���");
			break;
		case MyConstants.ORDER_STATE_WANCHENG:
			orderStateTextView.setText("����״̬�������");
			
			break;
		
		case MyConstants.ORDER_STATE_YIQUXIAO:
			orderStateTextView.setText("����״̬����ȡ��");
			logisticsLayout.setVisibility(View.GONE);//����������Ϣ
			cancleReasonLayout.setVisibility(View.VISIBLE);//��ʾȡ������ԭ��
			break;
			
		case MyConstants.ORDER_STATE_QUXIAO_SHENAQINF:
			orderStateTextView.setText("����״̬������ȡ��");
			
			break;
		case MyConstants.ORDER_STATE_TUIHUO_SHENAQINF:
			orderStateTextView.setText("����״̬�������˻�");
			
			break;
		case MyConstants.ORDER_STATE_TUIHUO_ZHONG:
			orderStateTextView.setText("����״̬���˻���");
			returnGoodsLayout.setVisibility(View.VISIBLE);//��ʾ�˻���Ϣ
			break;
		case MyConstants.ORDER_STATE_YITUIHUO:
			orderStateTextView.setText("����״̬�����˻�");
			returnGoodsLayout.setVisibility(View.VISIBLE);//��ʾ�˻���Ϣ
			break;

		default:
			break;
		}
		seqTextView.setText(orderTotalInfo.getOrderSeq());
		creatTimeTextView.setText("�������ڣ�"+orderTotalInfo.getOrderTime());
		
		orderGoodsInfosList = orderTotalInfo.getGoodsInfosList();
		orderGoodsInfoListAdapter = new OrderGoodsInfoListAdapter(mContext, orderGoodsInfosList);
		goodsListView.setAdapter(orderGoodsInfoListAdapter);
		MyUtility.setListViewHeightBasedOnChildren(goodsListView);//c��������listview�߶�
		mainScrollView.smoothScrollTo(0, 0); //��scrollview��������ͷ��ʾ
		
		//������Ϣ����
		consigneeNameTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getConsumerName());
		consigneeTelTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getTel());
		consigneeAddressTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getAddress());
		
		logicsticsCompanyTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getLogisticsCompany());
		logicsticsSerialTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getLogisticsSerial());
		deliverTimeTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getDeliverTime());
		
		if (orderTotalInfo.getOrderState() == MyConstants.ORDER_STATE_WANCHENG) {
			receiveTimeLayout.setVisibility(View.VISIBLE);
			receiveTimeTextView.setText(orderTotalInfo.getOrderInvoiceInfo().getReceiveTime());
		}
		
		if (orderTotalInfo.getOrderState() == MyConstants.ORDER_STATE_YIQUXIAO) {
			cancleReasonTextView.setText(orderTotalInfo.getOrderCancleReason());
		}
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.order_detail_info__title_back://���巵�ذ���
				back();
				break;

			default:
				break;
			}
		}
	}
	
	private void back() {
		super.onBackPressed();
	}

}
