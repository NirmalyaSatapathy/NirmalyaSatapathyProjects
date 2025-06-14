package com.java.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.asset.model.Asset;
import com.java.asset.util.ConnectionHelper;

public class AssetDaoImpl implements AssetDao{

	Connection con;
	PreparedStatement pst;
	private static final Logger log = Logger.getLogger("com.java.asset.dao.AssetDaoImpl");
	@Override
	public String addAsset(Asset asset) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();
	    String cmd = "INSERT INTO Asset (AssetName, Description, TotalQuantity, AvailableQuantity,createdBy,type) VALUES (?, ?, ?, ?,?,?)";
	    PreparedStatement pstmt = con.prepareStatement(cmd);
	      	pstmt.setString(1, asset.getAssetName());
	        pstmt.setString(2, asset.getDescription());
	        pstmt.setInt(3, asset.getTotalQuantity());
	        pstmt.setInt(4, asset.getAvailQuantity());
	        pstmt.setString(5,asset.getCreatedBy());
	        pstmt.setString(6,asset.getAssetType());
	        int rowsInserted = pstmt.executeUpdate();
	        con.close();
	        if (rowsInserted > 0) {
	        	log.info("Asset added successfully.");
	            return "AdminDashboard?faces-redirect=true";
	        } else {
	            return "Failed to add asset.";
	        }
	    }
	@Override
	public List<Asset> showAssets() throws ClassNotFoundException, SQLException {
	    List<Asset> assetList = new ArrayList<>();
	    Connection con = ConnectionHelper.getConnection();
	    String sql = "SELECT * FROM Asset WHERE AvailableQuantity > 0";
	    PreparedStatement pstmt = con.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();
	    while (rs.next()) {
	        Asset asset = new Asset();
	        asset.setAssetName(rs.getString("AssetName"));
	        asset.setDescription(rs.getString("Description"));
	        asset.setCreatedBy(rs.getString("createdBy"));
	        asset.setTotalQuantity(rs.getInt("TotalQuantity"));
	        asset.setAvailQuantity(rs.getInt("AvailableQuantity"));
	        asset.setAssetType(rs.getString("type"));
	        assetList.add(asset);
	    }
	    con.close();
	    return assetList;
	}
	@Override
	public Asset searchAsset(int AssetID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Asset> searchAsset(String type) throws ClassNotFoundException, SQLException {
	    List<Asset> assetList = new ArrayList<>();
	  log.info("inside impl layer for searchasset");
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	   log.info("type is"+type);
	    try {
	        con = ConnectionHelper.getConnection();
	        String sql = "SELECT AssetName, type, Description, TotalQuantity, AvailableQuantity, createdBy FROM Asset WHERE type LIKE ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1,type);  

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Asset asset = new Asset();
	            asset.setAssetName(rs.getString("AssetName"));
	            asset.setAssetType(rs.getString("type"));
	            asset.setDescription(rs.getString("Description"));
	            asset.setTotalQuantity(rs.getInt("TotalQuantity"));
	            asset.setAvailQuantity(rs.getInt("AvailableQuantity"));
	            asset.setCreatedBy(rs.getString("createdBy"));

	            assetList.add(asset);
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (con != null) con.close();
	    }
	    return assetList;
	}
	@Override
	public List<String> getDistinctTypes() throws ClassNotFoundException, SQLException {
        List<String> types = new ArrayList<>();
        
        String sql = "SELECT DISTINCT type FROM Asset";
        
        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                types.add(rs.getString("type"));
            }
        }
        
        return types;
    }


}
