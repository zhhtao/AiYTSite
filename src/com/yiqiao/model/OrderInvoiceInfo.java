package com.yiqiao.model;

import java.io.Serializable;

//������Ϣ
public class OrderInvoiceInfo implements Serializable{
	private String consumerName;//�ͻ�����
	private String tel;//�绰
	private String address;//��ַ
	
	private String deliverTime;//����ʱ��
	private String receiveTime;//�ջ�ʱ��
	private String logisticsCompany;//������˾
	private String logisticsSerial;//��������
	
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public String getLogisticsSerial() {
		return logisticsSerial;
	}
	public void setLogisticsSerial(String logisticsSerial) {
		this.logisticsSerial = logisticsSerial;
	}
	
	
}
