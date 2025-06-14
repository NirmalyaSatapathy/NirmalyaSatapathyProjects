package com.java.asset.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.bal.AssetBal;
import com.java.asset.model.Asset;

public class AssetController {
private static AssetBal assetBal;
public static Logger getLog() {
	return log;
}
private static final Logger log = Logger.getLogger("com.java.asset.Controller.AssetController");
public AssetBal getAssetBal() {
	return assetBal;
}
private String selectedType;

public String getSelectedType() {
    return selectedType;
}

public void setSelectedType(String selectedType) {
    this.selectedType = selectedType;
}

public void setAssetBal(AssetBal assetBal) {
	this.assetBal = assetBal;
}
public String addAssetController(Asset asset) throws ClassNotFoundException, SQLException
{
	log.info("new asset added controller layer");
	return assetBal.addAssetBal(asset);
	
}
public List<Asset> showAssetsController() throws ClassNotFoundException, SQLException
{
	log.info("assets shown controller layer");
	return assetBal.showAssetsBal();
}
public List<Asset> searchAssetByTypeController(String type) throws ClassNotFoundException, SQLException
{
	log.info("asset by type shown in controller");
	return assetBal.searchAssetByTypeBal(type);
}
public List<String> getDistinctTypesController() throws ClassNotFoundException, SQLException
{
	log.info("asset list returned in controller");
	return assetBal.getDistinctAssetTypesBal();
}
}
