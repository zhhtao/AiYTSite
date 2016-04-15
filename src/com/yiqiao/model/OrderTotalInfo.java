package com.yiqiao.model;

import java.io.Serializable;
import java.util.List;

//��������Ϣ
public class OrderTotalInfo implements Serializable{
	private String orderSeq;//������
	private String orderTime;//��������ʱ��
	private String orderCancleReason;//����ȡ��ԭ��
	
	public String getOrderCancleReason() {
		return orderCancleReason;
	}
	public void setOrderCancleReason(String orderCancleReason) {
		this.orderCancleReason = orderCancleReason;
	}
	private int orderState; //����״̬
	private double duesPayMoney;//Ӧ�����
	private double realPayMoney;//ʵ�����
	
	private List<OrderGoodsInfo> goodsInfosList;
	private OrderInvoiceInfo orderInvoiceInfo;
	
	public List<OrderGoodsInfo> getGoodsInfosList() {
		return goodsInfosList;
	}
	public void setGoodsInfosList(List<OrderGoodsInfo> goodsInfosList) {
		this.goodsInfosList = goodsInfosList;
	}
	public OrderInvoiceInfo getOrderInvoiceInfo() {
		return orderInvoiceInfo;
	}
	public void setOrderInvoiceInfo(OrderInvoiceInfo orderInvoiceInfo) {
		this.orderInvoiceInfo = orderInvoiceInfo;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getOrderState() {
		return orderState;
	}
	public void setOrderState(int orderState) {
		this.orderState = orderState;
	}
	public double getDuesPayMoney() {
		return duesPayMoney;
	}
	public void setDuesPayMoney(double duesPayMoney) {
		this.duesPayMoney = duesPayMoney;
	}
	public double getRealPayMoney() {
		return realPayMoney;
	}
	public void setRealPayMoney(double realPayMoney) {
		this.realPayMoney = realPayMoney;
	}
	
	
}
