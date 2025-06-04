package com.java.asset.dao;

import java.util.List;

import com.java.asset.model.AssetManager;

public interface IssueAssetDao {
public String issueAssetDao(AssetManager issueAsset);
public String returnAsset(AssetManager returnAsset);
public List<AssetManager> searchIssuesByEmployId(int EmployeeID);
}
