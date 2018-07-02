package com.nidhin.connections.model;

public class FrequencyUsageInfo {
	
	
	private Long inUseConnectionId;
	private String inUseConnectionName;
	private String connectionCategory;
	
	
	public Long getInUseConnectionId() {
		return inUseConnectionId;
	}
	public void setInUseConnectionId(Long inUseConnectionId) {
		this.inUseConnectionId = inUseConnectionId;
	}
	
	public String getInUseConnectionName() {
		return inUseConnectionName;
	}
	public void setInUseConnectionName(String inUseConnectionName) {
		this.inUseConnectionName = inUseConnectionName;
	}
	public String getConnectionCategory() {
		return connectionCategory;
	}
	public void setConnectionCategory(String connectionCategory) {
		this.connectionCategory = connectionCategory;
	}
}
