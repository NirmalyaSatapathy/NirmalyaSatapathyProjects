package com.java.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.asset.model.AssetManager;
import com.java.asset.model.Employ;
import com.java.asset.model.ReturnAsset;
import com.java.asset.util.ConnectionHelper;

public class AssetManagerDaoImpl implements AssetManagerDao {
	@Override
	public String issueAsset(AssetManager assetManager) throws ClassNotFoundException, SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	        con = ConnectionHelper.getConnection();

	        // 1. Get AssetID from AssetName
	        String getAssetIdSQL = "SELECT AssetID FROM Asset WHERE AssetName = ?";
	        pstmt = con.prepareStatement(getAssetIdSQL);
	        pstmt.setString(1, assetManager.getAssetName());
	        rs = pstmt.executeQuery();
	        int assetId = 0;
	        if (rs.next()) {
	            assetId = rs.getInt("AssetID");
	        } else {
	            return "AssetNotFound";
	        }
	        rs.close();
	        pstmt.close();

	        // 2. Get EmployeeID from Username
	        String getEmployeeIdSQL = "SELECT EmployeeID FROM Employee WHERE Username = ?";
	        pstmt = con.prepareStatement(getEmployeeIdSQL);
	        pstmt.setString(1, assetManager.getEmployName());
	        rs = pstmt.executeQuery();
	        int employeeId = 0;
	        if (rs.next()) {
	            employeeId = rs.getInt("EmployeeID");
	        } else {
	            return "EmployeeNotFound";
	        }
	        rs.close();
	        pstmt.close();

	        // 3. Insert into AssetAssignment
	        String insertSQL = "INSERT INTO AssetAssignment (AssetID, EmployeeID, AssignedDate) VALUES (?, ?, ?)";
	        pstmt = con.prepareStatement(insertSQL);
	        pstmt.setInt(1, assetId);
	        pstmt.setInt(2, employeeId);
	        pstmt.setDate(3, new java.sql.Date(assetManager.getAssignedDate().getTime()));
	        int rowsInserted = pstmt.executeUpdate();
	        pstmt.close();

	        if (rowsInserted > 0) {
	            // 4. Decrease AvailableQuantity in Asset table
	            String updateAssetSQL = "UPDATE Asset SET AvailableQuantity = AvailableQuantity - 1 WHERE AssetID = ? AND AvailableQuantity > 0";
	            pstmt = con.prepareStatement(updateAssetSQL);
	            pstmt.setInt(1, assetId);
	            int rowsUpdated = pstmt.executeUpdate();
	            pstmt.close();

	            if (rowsUpdated > 0) {
	                return "AdminDashboard?faces-redirect=true";
	            } else {
	                return "NoAvailableAssets";
	            }
	        } else {
	            return "IssueFailed";
	        }
	}
	public boolean isAssetAssignedToUser(String assetName, String employName) throws SQLException, ClassNotFoundException {
	    Connection con1 = ConnectionHelper.getConnection();
	    String sql = "SELECT COUNT(*) FROM AssetAssignment aa " +
	                 "JOIN Asset a ON aa.AssetID = a.AssetID " +
	                 "JOIN Employee e ON aa.EmployeeID = e.EmployeeID " +
	                 "WHERE a.AssetName = ? AND e.Username = ? AND aa.status IN ('pending', 'approved')";
	    PreparedStatement pstmt1 = con1.prepareStatement(sql);
	    pstmt1.setString(1, assetName);
	    pstmt1.setString(2, employName);
	    ResultSet rs1 = pstmt1.executeQuery();
	    boolean assigned = false;
	    if (rs1.next()) {
	        assigned = rs1.getInt(1) > 0;
	    }
	    rs1.close();
	    pstmt1.close();
	    con1.close();
	    return assigned;
	}


	@Override
	public String returnAsset(ReturnAsset request) throws ClassNotFoundException, SQLException {
	    Connection con = ConnectionHelper.getConnection();
	    System.out.println("in impl layer");
	    // Step 1: Get EmployeeID using username + email
	    String getEmpSql = "SELECT EmployeeID FROM Employee WHERE Username = ? AND Email = ?";
	    PreparedStatement pstmt = con.prepareStatement(getEmpSql);
	    pstmt.setString(1, request.getName());
	    pstmt.setString(2, request.getEmail());
	    ResultSet rs = pstmt.executeQuery();

	    int employeeId = -1;
	    if (rs.next()) {
	        employeeId = rs.getInt("EmployeeID");
	    } else {
	        con.close();
	        return "Employee not found.";
	    }

	    // Step 2: Get AssetID using assetName
	    String getAssetSql = "SELECT AssetID FROM Asset WHERE AssetName = ?";
	    pstmt = con.prepareStatement(getAssetSql);
	    pstmt.setString(1, request.getAsset());
	    rs = pstmt.executeQuery();

	    int assetId = -1;
	    if (rs.next()) {
	        assetId = rs.getInt("AssetID");
	    } else {
	        con.close();
	        return "Asset not found.";
	    }

	    // Step 3: Update AssetAssignment
	    String updateAssignmentSql = "UPDATE AssetAssignment SET ReturnDate = ?, status = 'returned' " +
                "WHERE AssetID = ? AND EmployeeID = ? AND status IN ('pending', 'approved')";

	    pstmt = con.prepareStatement(updateAssignmentSql);
	    pstmt.setDate(1, new java.sql.Date(request.getReturnDate().getTime())); // converting java.util.Date to java.sql.Date
	    pstmt.setInt(2, assetId);
	    pstmt.setInt(3, employeeId);
	    int rowsUpdated = pstmt.executeUpdate();

	    if (rowsUpdated == 0) {
	        con.close();
	        return "No active assignment found for this asset.";
	    }

	    // Step 4: Update AvailableQuantity
	    String updateAssetSql = "UPDATE Asset SET AvailableQuantity = AvailableQuantity + 1 WHERE AssetID = ?";
	    pstmt = con.prepareStatement(updateAssetSql);
	    pstmt.setInt(1, assetId);
	    pstmt.executeUpdate();
	    System.out.println("impl done");
	    con.close();
	    return "AdminDashboard?faces-redirect=true";
	}

	@Override
	public String approveAsset(ReturnAsset approval) throws SQLException, ClassNotFoundException {
	    Connection con = ConnectionHelper.getConnection();

	    // Get EmployeeID
	    String empSql = "SELECT EmployeeID FROM Employee WHERE Username = ? AND Email = ?";
	    PreparedStatement pstmt = con.prepareStatement(empSql);
	    pstmt.setString(1, approval.getName());
	    pstmt.setString(2, approval.getEmail());
	    ResultSet rs = pstmt.executeQuery();
	    int employeeId = -1;
	    if (rs.next()) employeeId = rs.getInt("EmployeeID");
	    else { con.close(); return "Employee not found."; }

	    // Get AssetID
	    String assetSql = "SELECT AssetID FROM Asset WHERE AssetName = ?";
	    pstmt = con.prepareStatement(assetSql);
	    pstmt.setString(1, approval.getAsset());
	    rs = pstmt.executeQuery();
	    int assetId = -1;
	    if (rs.next()) assetId = rs.getInt("AssetID");
	    else { con.close(); return "Asset not found."; }

	    // Approve the pending request
	    String updateSql = "UPDATE AssetAssignment SET status = 'approved' " +
	                       "WHERE AssetID = ? AND EmployeeID = ? AND status = 'pending'";
	    pstmt = con.prepareStatement(updateSql);
	    pstmt.setInt(1, assetId);
	    pstmt.setInt(2, employeeId);
	    int updated = pstmt.executeUpdate();
	    con.close();

	    return updated > 0 ? "AdminDashboard?faces-redirect=true" : "No pending request found.";
	}

	@Override
	public List<AssetManager> showPendingRequests() throws ClassNotFoundException, SQLException {
	    List<AssetManager> pendingRequests = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        con = ConnectionHelper.getConnection();
	        String sql = "SELECT a.AssetName, e.UserName, aa.AssignedDate, aa.ReturnDate, aa.status " +
	                     "FROM AssetAssignment aa " +
	                     "JOIN Asset a ON aa.AssetID = a.AssetID " +
	                     "JOIN Employee e ON aa.EmployeeID = e.EmployeeID " +
	                     "WHERE aa.status = 'pending'";
	        
	        pstmt = con.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            AssetManager request = new AssetManager();
	            request.setAssetName(rs.getString("AssetName"));
	            request.setEmployName(rs.getString("UserName"));
	            request.setAssignedDate(rs.getTimestamp("AssignedDate"));
	            request.setReturnDate(rs.getTimestamp("ReturnDate"));
	            request.setStatus(rs.getString("status"));
	            
	            pendingRequests.add(request);
	        }
	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (con != null) con.close();
	    }
	    return pendingRequests;
	}

	@Override
	public List<AssetManager> showAssetHistory(ReturnAsset asset) throws Exception {
	    List<AssetManager> list = new ArrayList<>();
	    Connection con = ConnectionHelper.getConnection();

	    String sql = "SELECT aa.AssignmentID, aa.AssetID, aa.EmployeeID, a.AssetName, e.Username, " +
	                 "aa.AssignedDate, aa.ReturnDate, aa.status " +
	                 "FROM AssetAssignment aa " +
	                 "JOIN Asset a ON aa.AssetID = a.AssetID " +
	                 "JOIN Employee e ON aa.EmployeeID = e.EmployeeID " +
	                 "WHERE e.Username = ? AND e.Email = ? " +
	                 "ORDER BY aa.AssignedDate DESC";

	    PreparedStatement pstmt = con.prepareStatement(sql);
	    pstmt.setString(1, asset.getName());
	    pstmt.setString(2, asset.getEmail());

	    ResultSet rs = pstmt.executeQuery();

	    while (rs.next()) {
	        AssetManager am = new AssetManager();
	        am.setAssetName(rs.getString("AssetName"));  // <-- Here you set assetName from Asset table
	        am.setEmployName(rs.getString("Username"));
	        am.setAssignedDate(rs.getTimestamp("AssignedDate"));
	        am.setReturnDate(rs.getTimestamp("ReturnDate"));
	        am.setStatus(rs.getString("status"));
	        list.add(am);
	    }

	    con.close();
	    return list;
	}


	


}
