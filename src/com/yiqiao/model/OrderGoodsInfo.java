package com.yiqiao.model;

import java.io.Serializable;

//������Ʒ��Ϣ
public class OrderGoodsInfo implements Serializable{
	private String goodsName;//��Ʒ��
	private String type;//���
	private String attribute;//����
	private String imageUrl;//ͼƬ��ַ
	private double price; //����
	private int nums;  //��������
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	
}
