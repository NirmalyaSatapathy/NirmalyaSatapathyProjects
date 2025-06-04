package com.java.asset.model;

public class Asset {
//	AssetID INT PRIMARY KEY AUTO_INCREMENT,
//    AssetName VARCHAR(100) NOT NULL,
//    Description TEXT,
//    TotalQuantity INT NOT NULL,
//    AvailableQuantity INT NOT NULL
	private String assetName;
	private String assetType;
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	private String description;
	private int totalQuantity;
	private int availQuantity;
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public int getAvailQuantity() {
		return availQuantity;
	}
	public void setAvailQuantity(int availQuantity) {
		this.availQuantity = availQuantity;
	}
	public Asset(String assetName, String assetType, String description, int totalQuantity, int availQuantity,
			String createdBy) {
		super();
		this.assetName = assetName;
		this.assetType = assetType;
		this.description = description;
		this.totalQuantity = totalQuantity;
		this.availQuantity = availQuantity;
		this.createdBy = createdBy;
	}
	public Asset() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
