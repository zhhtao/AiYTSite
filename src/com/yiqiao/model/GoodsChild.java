package com.yiqiao.model;

import java.io.Serializable;

import com.yiqiao.util.MyConstants;

public class GoodsChild implements Serializable{
	private String name;//����
	private String type;//���
	private String color;//��ɫ
	private String classify;//����
	private double price;//�۸�
	private int saleMonth,saleAll;//������ ������
	private int stock;//���п��
	
	private String imagePath;
	
	public String getImagePath() {
		return MyConstants.Test_SERVICE_IP + imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSaleMonth() {
		return saleMonth;
	}
	public void setSaleMonth(int saleMonth) {
		this.saleMonth = saleMonth;
	}
	public int getSaleAll() {
		return saleAll;
	}
	public void setSaleAll(int saleAll) {
		this.saleAll = saleAll;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
