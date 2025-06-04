package com.java.asset.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.bal.AssetBal;
import com.java.asset.bal.AssetManagerBal;
import com.java.asset.model.AssetManager;
import com.java.asset.model.ReturnAsset;

public class AssetManagerController {
private AssetManagerBal assetManagerBal;
private List<AssetManager> assetHistoryList;
private static final Logger log = Logger.getLogger("com.java.asset.Controller.AssetManagerController");

public AssetManagerBal getAssetManagerBal() {
	return assetManagerBal;
}


public void setAssetManagerBal(AssetManagerBal assetManagerBal) {
	this.assetManagerBal = assetManagerBal;
}


public String issueAssetController(AssetManager assetManager) throws ClassNotFoundException, SQLException
{
	log.info("asset issued in controller layer");
	return assetManagerBal.issueAssetBal(assetManager);
}
public String returnAssetController(ReturnAsset asset) throws ClassNotFoundException, SQLException
{
	log.info("asset returned in controller layer");
	return assetManagerBal.returnAssetBal(asset);
}
public List<AssetManager> showPendingRequestsController() throws ClassNotFoundException, SQLException
{
	log.info("pending requests shown in controller layer");
	return assetManagerBal.showPendingRequestsBal();
}
public String approveAssetController(ReturnAsset asset) throws ClassNotFoundException, SQLException
{
	log.info("asset approved in controller layer");
	return assetManagerBal.approveAssetBal(asset);
}
public String showAssetHistoryController(ReturnAsset asset) throws Exception
{
	log.info("asset history shown in controller layer");
	setAssetHistoryList(assetManagerBal.showAssetHistoryBal(asset));
	return null;
	
}


public List<AssetManager> getAssetHistoryList() {
	return assetHistoryList;
}


public void setAssetHistoryList(List<AssetManager> assetHistoryList) {
	this.assetHistoryList = assetHistoryList;
}
}
