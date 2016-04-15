package com.yiqiao.util;

import java.io.File;

import android.os.Environment;

public class MyConstants {
	
	public static final String SD_ROOT_PATH = Environment.getExternalStorageDirectory()+File.separator;
	public static final String Test_SERVICE_IP = "http://192.168.0.111";
	public static final String IMAGE1_PATH = "http://104.224.136.25/apple.jpg";
	public static final String IMAGE2_PATH = "http://192.168.0.111";
//	public static final String Test_SERVICE_IP = "http://10.0.2.2";
	
	public static final int FROM_ROU = 1000;
	public static final int FROM_QIN = 1001;
	public static final int FROM_DAN = 1002;
	public static final int FROM_SHUCAI = 1003;
	public static final int FROM_SHUIGUO = 1004;
	public static final int FROM_LIANGYOU = 1005;
	public static final int FROM_QITA = 1006;
	
	
	//����״̬
	public static final int ORDER_STATE_WEIFAHUO = 1;//δ����
	public static final int ORDER_STATE_YIFAHUO = 2;//�ѷ���
	public static final int ORDER_STATE_YIQUXIAO = 3;//��ȡ��
	public static final int ORDER_STATE_WANCHENG = 4;//���
	public static final int ORDER_STATE_QUXIAO_SHENAQINF = 5;//ȡ����������
	public static final int ORDER_STATE_TUIHUO_SHENAQINF = 6;//�˻�����
	public static final int ORDER_STATE_TUIHUO_ZHONG = 7;//�˻���
	public static final int ORDER_STATE_YITUIHUO = 8;//���˻�
	
	public static String currentUserName;
	public static String currentUserPassword;
}
