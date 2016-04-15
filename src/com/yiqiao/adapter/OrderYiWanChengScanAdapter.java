package com.yiqiao.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiqiao.adapter.OrderYiFaHuoScanAdapter.ViewHolder;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.OrderTotalInfo;
import com.yiqiao.order.logistics.OrderDetailInfoActivity;
import com.yiqiao.util.MyConstants;

public class OrderYiWanChengScanAdapter extends BaseAdapter{
	private List<OrderTotalInfo> orderTotalInfosList;
	private Context mContext;
	
	String ImageUrl = MyConstants.IMAGE1_PATH;
	DisplayImageOptions options = new DisplayImageOptions.Builder()
	.showImageOnLoading(R.drawable.load_download)
	.showImageOnFail(R.drawable.load_error)
	.cacheInMemory(true)
	.cacheOnDisc(true)
	.bitmapConfig(Bitmap.Config.RGB_565)
	.build();
	
	
	public OrderYiWanChengScanAdapter(Context context, List<OrderTotalInfo> orderTotalInfos) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.order_yi_wan_cheng_scan_list_item, null);
			viewHolder.orderSeq = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_seq);
			viewHolder.goodsName = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_goods_name);
			viewHolder.goodsAttri = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_goods_attri);
			viewHolder.priceValue = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_price_value);
			viewHolder.goodsAmount = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_amount);
			viewHolder.creatTime = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_create_time);
			viewHolder.totalMoney = (TextView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_total_money);
			
			viewHolder.goodsImageView = (ImageView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_goods_image);
			viewHolder.toDetailImage = (ImageView) convertView.findViewById(R.id.order_yi_wan_cheng_list_item_arrow_detail);
			
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
		viewHolder.toDetailImage.setTag(position);
		
		//��Ӽ����¼�
		viewHolder.toDetailImage.setOnClickListener(new MyOnClickListener());
		//ͼƬ
		ImageLoader.getInstance().displayImage(ImageUrl, viewHolder.goodsImageView, options);
		return convertView;
	}
	
	class ViewHolder {
		TextView orderSeq, goodsName,goodsAttri, priceValue, goodsAmount,creatTime,totalMoney;
		ImageView goodsImageView, toDetailImage;
	}
	
	private class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int ps = (Integer)v.getTag();
			switch (v.getId()) {
			case R.id.order_yi_wan_cheng_list_item_arrow_detail:
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
