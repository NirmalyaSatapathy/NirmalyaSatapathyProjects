package com.java.asset.bal;

import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.java.asset.dao.AssetDao;
import com.java.asset.dao.AssetDaoImpl;
import com.java.asset.model.Asset;

public class AssetBal {
private AssetDao assetDao;
private static final Logger log = Logger.getLogger("com.java.asset.bal.AssetBal");
public AssetDao getAssetDao() {
	return assetDao;
}
public void setAssetDao(AssetDao assetDao) {
	this.assetDao = assetDao;
}
public String addAssetBal(Asset asset) throws ClassNotFoundException, SQLException
{
	String createdBy=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("adminName");
	asset.setCreatedBy(createdBy);
	log.info("asset added from bal");
	return assetDao.addAsset(asset);
}
public List<Asset> showAssetsBal() throws ClassNotFoundException, SQLException
{
	log.info("assets shown from bal");
	return  assetDao.showAssets();
	
}
public List<Asset> searchAssetByTypeBal(String type) throws ClassNotFoundException, SQLException
{
	log.info("asset by type shown in bal");
	return assetDao.searchAsset(type);
}
public List<String> getDistinctAssetTypesBal() throws ClassNotFoundException, SQLException {
    // Implement your DAO call to fetch distinct 'type' values from Asset table.
    // For example:
	log.info("returning assetlist from bal layer");
    return assetDao.getDistinctTypes();
}

}
