package com.yiqiao.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiqiao.activity.StartActivity;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.order.logistics.OrderCancleOrderDialogActivity;
import com.yiqiao.order.logistics.OrderDeliverGoodsDialogActivity;
import com.yiqiao.order.logistics.OrderWeiFaHuoScanActivity;
import com.yiqiao.order.logistics.OrderDetailInfoActivity;
import com.yiqiao.util.MyConstants;
import com.yiqiao.util.MyToast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderWeiFaHuoScanAdapter extends BaseAdapter{

	private List<OrderTotalInfo> orderTotalInfosList;
	private Context mContext;
	
	String ImageUrl = "http://192.168.0.111/image/ya.jpg";
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.load_download)
	.showImageOnFail(R.drawable.load_error)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.build();
	
	
	public OrderWeiFaHuoScanAdapter(Context context, List<OrderTotalInfo> orderTotalInfos) {
		super();
		orderTotalInfosList = orderTotalInfos;
		mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orderTotalInfosList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return orderTotalInfosList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(position);
		// TODO Auto-generated method stub
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.order_wei_fa_huo_scan_list_item, null);
			viewHolder.orderSeq = (TextView) convertView.findViewById(R.id.my_order_list_item_seq);
			viewHolder.goodsName = (TextView) convertView.findViewById(R.id.my_order_list_item_goods_name);
			viewHolder.goodsAttri = (TextView) convertView.findViewById(R.id.my_order_list_item_goods_attri);
			viewHolder.priceValue = (TextView) convertView.findViewById(R.id.my_order_list_item_price_value);
			viewHolder.goodsAmount = (TextView) convertView.findViewById(R.id.my_order_list_item_amount);
			viewHolder.creatTime = (TextView) convertView.findViewById(R.id.my_order_list_item_create_time);
			viewHolder.totalMoney = (TextView) convertView.findViewById(R.id.my_order_list_item_total_money);
			
			viewHolder.cancleOrderButton = (Button) convertView.findViewById(R.id.my_order_list_item_cancle_order_btn);
			viewHolder.sendGoodsButton = (Button) convertView.findViewById(R.id.my_order_list_item_send_goods_btn);
			viewHolder.goodsImageView = (ImageView) convertView.findViewById(R.id.my_order_list_item_goods_image);
			viewHolder.toDetailImage = (ImageView) convertView.findViewById(R.id.my_order_list_item_arrow_detail);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.orderSeq.setText(orderTotalInfo.getOrderSeq());//������
		viewHolder.creatTime.setText("�������ڣ�"+orderTotalInfo.getOrderTime());//����ʱ��
		viewHolder.goodsName.setText(orderTotalInfo.getGoodsInfosList().get(0).getGoodsName());//�����Ʒʱ��ҳĬ����ʾ��һ��
		viewHolder.goodsAttri.setText(orderTotalInfo.getGoodsInfosList().get(0).getAttribute());//��Ʒ����
		viewHolder.goodsAmount.setText(orderTotalInfo.getGoodsInfosList().get(0).getNums()+"");//��Ʒ����
		viewHolder.priceValue.setText(orderTotalInfo.getGoodsInfosList().get(0).getPrice()+"");//��Ʒ����
		
		viewHolder.totalMoney.setText("ʵ����"+orderTotalInfo.getRealPayMoney());
		
		//Ϊ��ť��ӱ�ǩ
		viewHolder.cancleOrderButton.setTag(position);
		viewHolder.sendGoodsButton.setTag(position);
		viewHolder.toDetailImage.setTag(position);
		
		//��Ӽ����¼�
		viewHolder.cancleOrderButton.setOnClickListener(new MyOnClickListener());
		viewHolder.sendGoodsButton.setOnClickListener(new MyOnClickListener());
		viewHolder.toDetailImage.setOnClickListener(new MyOnClickListener());
		//ͼƬ
		ImageLoader.getInstance().displayImage(MyConstants.IMAGE1_PATH, viewHolder.goodsImageView, options);
		return convertView;
	}
	
	class ViewHolder {
		TextView orderSeq, goodsName,goodsAttri, priceValue, goodsAmount,creatTime,totalMoney;
		Button cancleOrderButton,sendGoodsButton;
		ImageView goodsImageView, toDetailImage;
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int ps = (Integer)v.getTag();
			switch (v.getId()) {
			case R.id.my_order_list_item_cancle_order_btn:
//				MyToast.toast(mContext, "cancle:"+ps, 0);
				Intent intent2 = new Intent(mContext, OrderCancleOrderDialogActivity.class);
				intent2.putExtra("position", ps);
				((OrderWeiFaHuoScanActivity)mContext).startActivityForResult(intent2, 1);//ȡ������
				break;
				
			case R.id.my_order_list_item_send_goods_btn:
//				MyToast.toast(mContext, "send:"+ps, 0);
				Intent intent3 = new Intent(mContext, OrderDeliverGoodsDialogActivity.class);
				intent3.putExtra("position", ps);
				((OrderWeiFaHuoScanActivity)mContext).startActivityForResult(intent3, 2);//��������
				break;
				
			case R.id.my_order_list_item_arrow_detail:
//				MyToast.toast(mContext, "detail:"+ps, 0);
				Intent intent = new Intent(mContext, OrderDetailInfoActivity.class);
				OrderTotalInfo orderTotalInfo = orderTotalInfosList.get(ps);
				intent.putExtra("orderTotalInfo", orderTotalInfo);
				mContext.startActivity(intent);
				
				break;

			default:
				break;
			}
		}
		
	}

}
