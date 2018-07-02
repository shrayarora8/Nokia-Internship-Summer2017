package com.nidhin.connections.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nidhin.connections.dao.ConnectionsDAO;
import com.nidhin.connections.model.Connection;
import com.nidhin.connections.model.FreqdataForPhysicalConnection;

public class JdbcConnectionsDAO implements ConnectionsDAO
{
	
	private JdbcTemplate jdbcTemplate;
	
	protected static final String NL = "\n";
	
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return namedJdbcTemplate;
	}

	public void setNamedJdbcTemplate(NamedParameterJdbcTemplate namedJdbcTemplate) {
		this.namedJdbcTemplate = namedJdbcTemplate;
	}

	
	public List<Connection> getConnDetails(long  connId) {
		System.out.println("Query execute entered");
		List<Connection> connData = null;
		
		try {
			String query = "SELECT connectionid,connectionname FROM networkconnection where  connectionid = :connectionid";
			System.out.println("Query execute entered" + query);
			Map<String, Object> input = new HashMap<String, Object>();
		    input.put("connectionid", Long.valueOf(connId));
		    
			 connData = this.getNamedJdbcTemplate().
					query(query, input, connRowMapper);
			
			System.out.println(connData.size());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return connData ;
		}
	
	public List<FreqdataForPhysicalConnection> getLcWavelengthUsage(Long id) {

		String query =
				"select  nc.connectionname otsname,nc.connectionid otsotnid,nnc.connectionid omsotnid,nc.connectiondirection otsdirection,nc.connectionshape otsshape," + NL +
				"anode1.name fromnode1,tpSrc.tpnativename fromPort1,znode1.name tonode1,tpSink.tpnativename toPort1,  " + NL +
				"anode2.name fromnode2,znode2.name  tonode2,tpSrc2.tpnativename fromPort2,tpSink2.tpnativename toPort2,lc.frequency frequency,las.endunit channelsize, " + NL +
				"one.availability avail1,bit_or(two.availability,one.availability) avail2, " + NL +
				"unc.connectionid inuseconnectionid,unc.connectionname inuseconnectionname,unc.connectioncategory,an.userlabel asonnpa " + NL +
				"from  " + NL +
				"lc lc1   " + NL +
				"join baselccomponent ml on (lc1.ncid = :ncid and (lc1.lcid=ml.lcid) and (ml.shapecomponenttype in (0,1,3,5)) )  " + NL +
				"left join networkconnection nc on (lc1.ncid = nc.connectionid) " + NL +
				"left join nccomponent ncc on (ncc.connectionid = nc.connectionid and ncc.nccomponenttype=1)  " + NL +
				"join tp tpSrc on (ncc.srctpid = tpSrc.tpid) join tp tpSink on (ncc.sinktpid = tpSink.tpid) " + NL +
				"left join node anode1 on (ncc.srcneid=anode1.handle) " + NL +
				"left join node znode1 on (ncc.sinkneid=znode1.handle) " + NL +
				"left join nccomponent ncc2 on (ncc2.connectionid = nc.connectionid and ncc2.nccomponenttype!=1) " + NL +
				"left join tp tpSrc2 on (ncc2.srctpid = tpSrc2.tpid) left join tp tpSink2 on (ncc2.sinktpid = tpSink2.tpid) " + NL +
				"left join node anode2 on (ncc2.srcneid=anode2.handle) " + NL +
				"left join node znode2 on (ncc2.sinkneid=znode2.handle) " + NL +
				"left join networkconnection nnc on (ml.inuseconnectionid = nnc.connectionid) " + NL +
				"join lc on (lc.ncid=nnc.connectionid) " + NL +
				"left join baselccomponent one on ((lc.lcid=one.lcid) and (one.shapecomponenttype in (0,1,3,5)))   " + NL +
				"left join baselccomponent two on ((lc.lcid=two.lcid) and (two.shapecomponenttype in (2,4,6)))   " + NL +
				"left outer join networkconnection unc on (one.inuseconnectionid = unc.connectionid) " + NL +
				"left join lcavailsum las on (lc.ncid=las.serverid) " + NL +  
				"left join ason_connec ac on (nnc.connectionid=ac.otnmapper) " + NL +
				"left join ason_transobjnpa transobj on (ac.connectId = transObj.transObjId) " + NL + 
		        "left join ason_npa an on(an.npaid=transobj.npaId) order by lc.frequency"
		         ;	
		
		Map<String,Object> input = new HashMap<String,Object>();
		input.put("ncid", id);

		List<FreqdataForPhysicalConnection> lcList = this.getNamedJdbcTemplate().query(query, input, waveLengthMapper);

		return lcList;
	}
	
	public List<Long> getOTSConnIDList() {
		String query= "select nc.connectionid otsConnId from networkconnection nc where nc.connectionrate = 30";	
		List<Long> otsList = this.getNamedJdbcTemplate().query(query, otsConnIdList);

		return otsList;
	}
	
	
		protected RowMapper<FreqdataForPhysicalConnection> waveLengthMapper = new RowMapper<FreqdataForPhysicalConnection>(){

    @Override
    public FreqdataForPhysicalConnection mapRow(ResultSet rs, int rownum) throws SQLException {
    	FreqdataForPhysicalConnection lcInfo = new FreqdataForPhysicalConnection();
    	lcInfo.setOtsName(rs.getString("otsname"));
    	lcInfo.setOtsotnid(rs.getLong("otsotnid"));
    	lcInfo.setOtsdirection(rs.getLong("otsdirection"));
    	lcInfo.setOtsshape(rs.getLong("otsshape"));
    	lcInfo.setOmsotnid(rs.getLong("omsotnid"));
    	lcInfo.setFromNode1(rs.getString("fromnode1"));
    	lcInfo.setFromPort1(rs.getString("fromPort1"));
    	lcInfo.setToNode1(rs.getString("tonode1"));
    	lcInfo.setToPort1(rs.getString("toPort1"));
    	lcInfo.setFromPort2(rs.getString("fromPort2"));
    	lcInfo.setToPort2(rs.getString("toPort2"));
    	lcInfo.setFromNode2(rs.getString("fromnode2"));
    	lcInfo.setToNode2(rs.getString("tonode2"));
    	lcInfo.setFrequency(rs.getString("frequency"));
    	lcInfo.setChannelSize(rs.getInt("channelsize"));
    	lcInfo.setAsonNPA(rs.getString("asonnpa"));
    	lcInfo.setAvail1(rs.getInt("avail1"));
    	lcInfo.setAvail2(rs.getInt("avail2"));
    	lcInfo.setConnectioncategory(rs.getInt("connectioncategory"));
    	lcInfo.setInuseconnectionname(rs.getString("inuseconnectionname"));
    	lcInfo.setInuseconnectionid(rs.getLong("inuseconnectionid"));
    	return lcInfo;
    }

};

protected RowMapper<Long> otsConnIdList = new RowMapper<Long>(){

    @Override
    public Long mapRow(ResultSet rs, int rownum) throws SQLException {
    	Long otsConnId=rs.getLong("otsConnId");
    	
    	return otsConnId;
    }

};
	
	private RowMapper<Connection> connRowMapper = new RowMapper<Connection>() {
	@Override
	public Connection mapRow(ResultSet rs, int rownum) throws SQLException{
		Connection connData = new Connection();
		connData.setConnId(rs.getLong("connectionid")); 
		connData.setConnectionName(rs.getString("connectionname"));
		//connData.setNcgroupid(rs.getLong("NCGROUPID"));
		return connData;
	}
};
	
	
}




