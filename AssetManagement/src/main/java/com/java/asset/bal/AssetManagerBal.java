package com.java.asset.bal;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.java.asset.dao.AssetManagerDao;
import com.java.asset.model.AssetManager;
import com.java.asset.model.ReturnAsset;

public class AssetManagerBal {
AssetManagerDao assetManagerDao;
private static final Logger log = Logger.getLogger("com.java.asset.bal.AssetManagerBal");
public AssetManagerDao getAssetManagerDao() {
	return assetManagerDao;
}

public void setAssetManagerDao(AssetManagerDao assetManagerDao) {
	this.assetManagerDao = assetManagerDao;
}
public String issueAssetBal(AssetManager assetManager) throws ClassNotFoundException, SQLException {
    FacesContext context = FacesContext.getCurrentInstance();

    // Basic null or empty checks - just in case
    if (assetManager.getAssetName() == null || assetManager.getAssetName().trim().isEmpty()) {
        context.addMessage("assetName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Asset Name is required", null));
        context.validationFailed();
        return null;
    }
    if (assetManager.getEmployName() == null || assetManager.getEmployName().trim().isEmpty()) {
        context.addMessage("employName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee Name is required", null));
        context.validationFailed();
        return null;
    }
    if (assetManager.getAssignedDate() == null) {
        context.addMessage("assignedDate", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Assigned Date is required", null));
        context.validationFailed();
        return null;
    }

    // Check if asset is already assigned to this user (you need to implement this method in DAO or BAL)
    boolean alreadyAssigned = assetManagerDao.isAssetAssignedToUser(assetManager.getAssetName(), assetManager.getEmployName());
    if (alreadyAssigned) {
        context.addMessage("assetName", new FacesMessage(FacesMessage.SEVERITY_ERROR, "This asset is already assigned/in pending state for you.", null));
        context.validationFailed();
        return null;
    }
    log.info("assert issued in bal ");
    // If all validations pass, call DAO to issue asset
    return assetManagerDao.issueAsset(assetManager);
}
public String returnAssetBal(ReturnAsset asset) throws ClassNotFoundException, SQLException
{
	log.info("asset returned in bal");
	return assetManagerDao.returnAsset(asset);
}
public List<AssetManager> showPendingRequestsBal() throws ClassNotFoundException, SQLException
{
	log.info("pending assets shown in bal");
	return assetManagerDao.showPendingRequests();
}
public String approveAssetBal(ReturnAsset asset) throws ClassNotFoundException, SQLException
{
	log.info(" asset approved successfully in bal");
	return assetManagerDao.approveAsset(asset);
}
public List<AssetManager> showAssetHistoryBal(ReturnAsset asset) throws Exception
{
	log.info("asset history for the employ shown in bal");;
	return assetManagerDao.showAssetHistory(asset);
}
}
