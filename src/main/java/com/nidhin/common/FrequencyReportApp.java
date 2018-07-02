package com.nidhin.common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.nidhin.connections.model.FrequencyUsageResponse;

public class FrequencyReportApp 
{
    public static void main( String[] args )
    {
    	
    		try {
		
   FrequencyReportAppImpl lImpl = new FrequencyReportAppImpl(); // class for fetching data fron DB
   System.out.println("Select Option to generate Report\n  1 : All Connections");
   Long connId= null;
   List<Long> connList = null;
   int type = 1;
   Scanner ss = new Scanner(System.in);
   type = ss.nextInt();
   if(type == 1)
   if (type == 0){ // To be enhanced
	   System.out.println("Enter Physical Connection Id : ");
	   connId = ss.nextLong();
	   connList = new ArrayList();
	   connList.add(connId);
   }
  
   FrequencyUsageResponse response =  lImpl.getLcWavelengthUsageforPhysicalConnection(connList ); // method points to fetch data from db
   String fileName = "C://Shray/FrequencyUsageReport_" + new SimpleDateFormat("ddhhmmssSSS'.csv'").format(new Date());
   System.out.println("Frequency data fetching completed");
   FrequencyReportInventoryHelper lHelper = new FrequencyReportInventoryHelper(); // contains method for witing csv file
   lHelper.writeToCSV(response.getOtsInfo(), fileName); //calling method to write file
   
   System.out.println("File Generated,,, Opening the file");
   Desktop.getDesktop().open(new File(fileName)); //opening
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    
        

        
    }
}
