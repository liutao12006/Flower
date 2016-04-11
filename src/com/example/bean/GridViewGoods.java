package com.example.bean;

public class GridViewGoods {
	//gridview 显示的商品的类
	private int id;
	private String  imageUri;
	private String  goodsname;
	private String  oidprice;
	private String  newprice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getOidprice() {
		return oidprice;
	}
	public void setOidprice(String oidprice) {
		this.oidprice = oidprice;
	}
	public String getNewprice() {
		return newprice;
	}
	@Override
	public String toString() {
		return "GridViewGoods [id=" + id + ", imageUri=" + imageUri
				+ ", goodsname=" + goodsname + ", oidprice=" + oidprice
				+ ", newprice=" + newprice + "]";
	}
	public void setNewprice(String newprice) {
		this.newprice = newprice;
	}
	public GridViewGoods(String imageUri, String goodsname, String oidprice,
			String newprice) {
		super();
		this.imageUri = imageUri;
		this.goodsname = goodsname;
		this.oidprice = oidprice;
		this.newprice = newprice;
	}
	
	public GridViewGoods(){}


}
