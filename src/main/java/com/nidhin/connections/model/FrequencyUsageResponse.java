package com.nidhin.connections.model;

import java.util.ArrayList;
import java.util.List;

public class FrequencyUsageResponse 
{
	
	List<FreqUsageInfo> otsInfo = new ArrayList<FreqUsageInfo>();

	public List<FreqUsageInfo> getOtsInfo() {
		return otsInfo;
	}

	public void setOtsInfo(List<FreqUsageInfo> otsInfo) {
		this.otsInfo = otsInfo;
	}

}
