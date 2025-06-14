package com.java.asset.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.asset.model.Asset;

public interface AssetDao {
public String addAsset(Asset asset) throws ClassNotFoundException, SQLException;
public List<Asset> showAssets() throws ClassNotFoundException, SQLException;
public Asset searchAsset(int AssetID);
public List<Asset> searchAsset(String type) throws ClassNotFoundException, SQLException;
public List<String> getDistinctTypes() throws ClassNotFoundException, SQLException;
}
