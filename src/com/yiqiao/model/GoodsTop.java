package com.yiqiao.model;

import java.io.Serializable;
import java.util.List;

public class GoodsTop implements Serializable{
	private String name;//����
	private Img img;//ͼƬ
	private int salesVolume;//����
	private List<GoodsChild> goodsChilds;//���������Ϣ
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Img getImg() {
		return img;
	}
	public void setImg(Img img) {
		this.img = img;
	}
	public int getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	public List<GoodsChild> getGoodsChilds() {
		return goodsChilds;
	}
	public void setGoodsChilds(List<GoodsChild> goodsChilds) {
		this.goodsChilds = goodsChilds;
	}
	
	
}
