package com.java.asset.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.asset.model.AssetManager;
import com.java.asset.model.Employ;
import com.java.asset.model.ReturnAsset;

public interface AssetManagerDao {
public String issueAsset(AssetManager assetManager) throws ClassNotFoundException, SQLException;
public String returnAsset(ReturnAsset asset) throws ClassNotFoundException, SQLException;
public String approveAsset(ReturnAsset asset) throws SQLException, ClassNotFoundException;
public List<AssetManager> showPendingRequests() throws ClassNotFoundException, SQLException;
public List<AssetManager> showAssetHistory(ReturnAsset asset) throws Exception;
public boolean isAssetAssignedToUser(String assetName, String employName) throws SQLException, ClassNotFoundException;
}
