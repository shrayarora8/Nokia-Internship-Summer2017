package com.nidhin.connections.dao;

import java.util.List;

import com.nidhin.connections.model.Connection;
import com.nidhin.connections.model.FreqdataForPhysicalConnection;

public interface ConnectionsDAO 
{

	public List<Connection> getConnDetails(long id) ;
	public List<FreqdataForPhysicalConnection> getLcWavelengthUsage(Long id) ;
	public List<Long> getOTSConnIDList();

}




