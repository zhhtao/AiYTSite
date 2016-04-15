package com.yiqiao.stockpile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yiqiao.activity.BaseActivity;
import com.yiqiao.adapter.GoodsChildAdapter;
import com.yiqiao.aiytsite.R;
import com.yiqiao.model.GoodsChild;

public class StockClassifyLevel2 extends BaseActivity{
	
	private static final int SHOW_INFO_LAYOUT = 1 , ADAPTER_CHANG = 2;
	private ImageView backImageView;
	private Context mContext;
	private List<GoodsChild> datas= new ArrayList<GoodsChild>();
	private GoodsChildAdapter goodsChildAdapter;
	private ListView listView;
	private TextView goodsName;
	private RelativeLayout progressBaRelativeLayout;
	private LinearLayout showInfoLayout;
	private String  goodsNamefromLevel1;
	private int listViewSelectedPosition;
	
	String[] strings = {"hello","nihao","good"};
	
	private Handler mHandler = new Handler() {
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_INFO_LAYOUT:
				
				goodsChildAdapter.notifyDataSetChanged();
				progressBaRelativeLayout.setVisibility(View.GONE);
				showInfoLayout.setVisibility(View.VISIBLE);
				
				break;
				
			case ADAPTER_CHANG:
				
				goodsChildAdapter.notifyDataSetChanged();
				
				break;

			default:
				break;
			}
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stock_classify_level2);
		mContext = this;
		goodsNamefromLevel1 = getIntent().getStringExtra("goodsNameFromLevel1");
		
		
		initView();
	}
	
	//���յ����޸���Ϣ�Ļ���ؽ��
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				
				int ps = data.getIntExtra("data_return_positon", 0);
				GoodsChild gChild = (GoodsChild) data.getSerializableExtra("data_return_goodsChild");
				
				//��������ϴ��޸ĺ�����
				
				//UI����
				datas.set(ps, gChild);
				mHandler.sendEmptyMessage(ADAPTER_CHANG);
			}
			break;
		default:
		}
	}
	
	private void initView() {
		//�л���ʾ���ȿ����ϸ��Ϣ
		progressBaRelativeLayout = (RelativeLayout) findViewById(R.id.stock_classify_level2_progressBar_layout);
		progressBaRelativeLayout.setVisibility(View.VISIBLE);
		showInfoLayout = (LinearLayout) findViewById(R.id.stock_classify_level2_show_layout);
		showInfoLayout.setVisibility(View.INVISIBLE);
		
		//��Ʒ����̬�޸�
		goodsName = (TextView) findViewById(R.id.stock_classify_level2_goodsName);
		goodsName.setText(goodsNamefromLevel1);
		
		//������Ʒ��Ϣ
		listView = (ListView) findViewById(R.id.stock_classify_level2_listView);
		goodsChildAdapter = new GoodsChildAdapter(mContext, datas);
		listView.setAdapter(goodsChildAdapter);
		ItemOnLongClick1();
		
		//���������Ʒ�ְ�ť����
		Button addNewGoodsButton = (Button) findViewById(R.id.stock_classify_level2_addNew);
		addNewGoodsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mContext, AddNewGoods.class);
				startActivity(intent);
			}
		});
		
		//���巵�ذ���
		backImageView = (ImageView) findViewById(R.id.stock_classify_level2_back);
		backImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				back();
			}
		});
		
		
		
		//��ȡ����
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//��������� ������ת���ӳ�
				SystemClock.sleep(100);
				initDatasFromServer(null);
				mHandler.sendEmptyMessage(SHOW_INFO_LAYOUT);
			}
		}).start();
		
	}
	
	
	private void  initDatasFromServer(String address) {
		//�ӷ�������������
		for (int i=0; i<5; i++) {
			GoodsChild goodsChild = new GoodsChild();
			goodsChild.setClassify("����");
			goodsChild.setColor("���ɫ");
			goodsChild.setPrice(28.8);
			goodsChild.setSaleAll(365);
			goodsChild.setSaleMonth(255);
			goodsChild.setStock(123);
			goodsChild.setImagePath(File.separator+"image"+File.separator+"muji.jpg");
			
			datas.add(goodsChild);
		}
		
	}
	
	private void back() {
		super.onBackPressed();
		
	}
	
	
	//listview���������˵�
	private void ItemOnLongClick1() {  
		//ע��setOnCreateContextMenuListener��������onContextItemSelected����ʹ�õ�  

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				listViewSelectedPosition = position;
				
				return false;
			}
		});
		listView.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {  

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.add(0,0,0,"�����Ʒ��");  
				menu.add(0,1,0,"ɾ����Ʒ��");  
				menu.add(0,2,0,"ȡ��");  
			}  
		});  
	}  

	// �����˵���Ӧ����  
	public boolean onContextItemSelected(MenuItem item) {  

		switch(item.getItemId()) {  
		
		case 0:  
			// ��Ӳ���  
			Toast.makeText(mContext,  
					"���",  
					Toast.LENGTH_SHORT).show();  
		break;  

		case 1:  
			// ɾ������  
			datas.remove(listViewSelectedPosition);
			mHandler.sendEmptyMessage(ADAPTER_CHANG);
			//֪ͨ����������
			
			break;  

		case 2:  
			// ȡ������  
			break;  
//
		default:  
			break;  
		}  
//
		return super.onContextItemSelected(item);  
//
	}  
}
