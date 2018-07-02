package com.nidhin.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nidhin.connections.dao.ConnectionsDAO;
import com.nidhin.connections.model.Connection;
import com.nidhin.connections.model.FreqUsageInfo;
import com.nidhin.connections.model.FreqdataForPhysicalConnection;
import com.nidhin.connections.model.FrequencyUsageInfo;
import com.nidhin.connections.model.FrequencyUsageResponse;

public class FrequencyReportAppImpl extends FrequencyReportApp
{

	ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	 
    ConnectionsDAO connectionsDAO = (ConnectionsDAO) context.getBean("connectionDAO");
  //  List<Connection> connectionList = connectionsDAO.getConnDetails(949);
    
  //  System.out.println("Query executed conname is " + connectionList.get(0).getConnectionName());
	
	public FrequencyUsageResponse getLcWavelengthUsageforPhysicalConnection(
			List<Long> connIdList) {


		FrequencyUsageResponse resp = new FrequencyUsageResponse();

		//LCWavelengthUsageInfo otsInfo = new LCWavelengthUsageInfo();
		if(connIdList==null || connIdList.size() ==0){
			connIdList=connectionsDAO.getOTSConnIDList();// get all list of OTS physical connections
		}
		List<FreqUsageInfo> otsInfoList = new ArrayList<FreqUsageInfo>();
				Iterator connItr = connIdList.iterator ();
				while (connItr.hasNext ()) {
					Long connId = (Long) connItr.next (); //iterating through id list to fetch frequency info
					List<FreqdataForPhysicalConnection> lcInfoList = connectionsDAO.getLcWavelengthUsage(connId);
					FreqUsageInfo otsInfo =  new FreqUsageInfo();
					
					//FreqdataForPhysicalConnection  = raw data from DB
					//FreqUsageInfo = CSV writable data formed from above raw data
				if(lcInfoList.size()>0){
					Iterator lcInfoItr = lcInfoList.iterator ();// iterating through connections list with frequency info for processing and making it in CSV writable form
					
					while (lcInfoItr.hasNext ()) {
						FreqdataForPhysicalConnection lcInfo = (FreqdataForPhysicalConnection) lcInfoItr.next();
						
						otsInfo.setOtsName(lcInfo.getOtsName());
						otsInfo.setChannelSize(lcInfo.getChannelSize());
						otsInfo.setFromNode1(lcInfo.getFromNode1());
						otsInfo.setFromPort1(lcInfo.getFromPort1());
						otsInfo.setToNode1(lcInfo.getToNode1());
						otsInfo.setToPort1(lcInfo.getToPort1());
						otsInfo.setFromNode2(lcInfo.getFromNode2());
						otsInfo.setToNode2(lcInfo.getToNode2());
						otsInfo.setFromPort2(lcInfo.getFromPort2());
						otsInfo.setToPort2(lcInfo.getToPort2());
						otsInfo.setAsonNPA(lcInfo.getAsonNPA());
						otsInfo.setOmsotnId(lcInfo.getOmsotnid());
						otsInfo.setOtsotnId(lcInfo.getOtsotnid());
						
						FrequencyUsageInfo freqInfo = new FrequencyUsageInfo(); //assigning data object to each frequency
						freqInfo.setInUseConnectionId(lcInfo.getInuseconnectionid());
						freqInfo.setInUseConnectionName(lcInfo.getInuseconnectionname());
						
						Long connDirection =lcInfo.getOtsdirection();
						Long connshape =lcInfo.getOtsshape();
						otsInfo.setOtsDirection("Bi");
						if(connDirection == 1){
							otsInfo.setOtsDirection("Uni");
						}
						if(connshape == 8){
							otsInfo.setOtsShape("Simple");
						}
						else if(connshape == 9){
							otsInfo.setOtsShape("Four Ended");
						}
						Integer connCatgry =lcInfo.getConnectioncategory(); 
						String conntype = "";
						if(connCatgry==1 || (lcInfo.getAvail1()!=null && lcInfo.getAvail1()==1)){
							conntype = "x";
						}else if(connCatgry==5||connCatgry==6||connCatgry==10||connCatgry==11||connCatgry==12
								||connCatgry==13||connCatgry==14||connCatgry==15){
							conntype = "n";
						}else if((lcInfo.getAvail1()!=null &&(lcInfo.getAvail1()==16 ||lcInfo.getAvail1()==32 || lcInfo.getAvail1()==2064 || lcInfo.getAvail1()==17))||
								(lcInfo.getAvail2()!=null &&(lcInfo.getAvail2()==16 ||lcInfo.getAvail2()==32 || lcInfo.getAvail2()==2064 || lcInfo.getAvail2()==17))){
							//2048-Reserver for ASON
							//2049- Used in ASON Trail
							//2064,17-Used By Cross Connection

							conntype = "u";
						}else if((lcInfo.getAvail1()!=null &&(lcInfo.getAvail1()==512 || lcInfo.getAvail1()==2560))){
							conntype = "n/a";
						}
						//Flexgrid update, To be revisited

						if(otsInfo.getChannelSize() == 768){
							otsInfo.setOtsName(lcInfo.getOtsName()+"*");							
							if(conntype!=null && conntype!= "" && !conntype.equals("n/a")){
								if(!lcInfo.getFrequency().endsWith(".000")){
									String slice=lcInfo.getFrequency();
									// +4 slices
									if(slice.endsWith(".500") || slice.endsWith(".125") ||
									   slice.endsWith(".750") ||slice.endsWith(".375"))
									{
									String freqsliceAfterTrim=slice.substring(0, slice.indexOf("."));	
									conntype=conntype+"*";
									freqInfo.setConnectionCategory(conntype); // can be reused in future
									otsInfo.getFrequencyMap().put(new Integer(freqsliceAfterTrim), conntype);
									}
									// -3 slices
									else if(slice.endsWith(".625") || slice.endsWith(".250") ||
											slice.endsWith(".875"))
									{
										String freqsliceAfterTrim=slice.substring(0, slice.indexOf("."));
										conntype=conntype+"*";
										if("n/a".equals(otsInfo.getFrequencyMap().get(new Integer(freqsliceAfterTrim)))){
											freqInfo.setConnectionCategory(conntype); // can be reused in future
											otsInfo.getFrequencyMap().put(new Integer(freqsliceAfterTrim), conntype);	
										}	
									}
								}
								else{	
									conntype=conntype+"*";
								}
							}
							if(lcInfo.getFrequency().endsWith(".000"))
							{
							freqInfo.setConnectionCategory(conntype); // can be reused in future
							otsInfo.getFrequencyMap().put(new Integer(lcInfo.getFrequency().substring(0, lcInfo.getFrequency().indexOf("."))), conntype);
							}
						}
						else{
							if(lcInfo.getFrequency().endsWith(".000"))
							{
							freqInfo.setConnectionCategory(conntype); // can be reused in future
							otsInfo.getFrequencyMap().put(new Integer(lcInfo.getFrequency().substring(0, lcInfo.getFrequency().indexOf("."))), conntype);
							}
							else if(lcInfo.getFrequency().indexOf(".") == -1){
								freqInfo.setConnectionCategory(conntype); // can be reused in future
								otsInfo.getFrequencyMap().put(new Integer(lcInfo.getFrequency()), conntype);	
							}
						}
				}
				}
				
				if(otsInfo.getChannelSize()!=null && (otsInfo.getChannelSize()==44 || otsInfo.getChannelSize() == 88 || otsInfo.getChannelSize() == 96 || otsInfo.getChannelSize() == 768))
				{
					otsInfoList.add(otsInfo);
				}
				
				}
				resp.setOtsInfo(otsInfoList);
		//resp.setOtsInfo(otsInfoList);

		return resp;
	}
    


}
