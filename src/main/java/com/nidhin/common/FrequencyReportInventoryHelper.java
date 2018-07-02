package com.nidhin.common;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nidhin.connections.model.FreqUsageInfo;



@Component
public class FrequencyReportInventoryHelper {

	
	// Set to true for Unit Testing w/o a real database
	static boolean useFakeDataOnly = false;
	private final String CSV_SEPARATOR = ",";
	private static final String OTS_NAME = "OTS Name";
	private static final String FROM_NODE = "From Node";
	private static final String FROMNODE_PORT = "From Port#1";
	private static final String TO_NODE = "To Node";
	private static final String TONODE_PORT = "To Port#1";
	private static final String SHAPE = "Shape";
	private static final String ASON_NPA = "ASON NPA";
	private static final String FROMNODE_PORT_2 = "From Port#2";
	private static final String TONODE_PORT_2 = "To Port#2";
	private static final String NUMBER_OF_CHANNELS = "Number Of Channels";


	void writeToCSV(List<FreqUsageInfo> productList, String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			writeCSVColumnHeaders(bw);
			for (FreqUsageInfo product : productList) {
				StringBuffer oneLine = new StringBuffer();
				String fromPort2 = product.getFromPort2() != null ? product.getFromPort2() : "";
				String toPort2 = product.getToPort2() != null ? product.getToPort2() : "";
				String asonNPA = product.getAsonNPA() != null ? product.getAsonNPA() : "";
				oneLine.append(appendDQ(product.getOtsName()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(product.getFromNode1()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(product.getToNode1()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(product.getFromPort1()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(product.getToPort1()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(fromPort2));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(toPort2));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(product.getOtsShape()));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(appendDQ(asonNPA));
				oneLine.append(CSV_SEPARATOR);
				oneLine.append(product.getChannelSize());
				HashMap<Integer, Object> freqMap = product.getFrequencyMap();
				for (Integer x = 9130; x <= 9605; x = x + 5) {
					oneLine.append(CSV_SEPARATOR);
					String value = (String) freqMap.get(x);
					oneLine.append(value);
				}
				bw.write(oneLine.toString());
				bw.newLine();
			}
			writeCSVLegend(bw);
			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	private void writeCSVLegend(BufferedWriter bw) {
		StringBuffer oneLine = new StringBuffer();
		oneLine.append("\n");
		oneLine.append("\n");
		oneLine.append("\n");
		oneLine.append("Legend\n" + "x - used by managed plane infrastructure optical channel\n"
				+ "n - used as nominal route of ASON connection\n"
				+ "u - uncorrelated XC present\n"
				+ "n/a - frequency not applicable\n"
				+ "Empty Cell - frequency is available\n"
				+ "* - FlexGrid supported Conn");
		try {
			bw.flush();
			bw.write(oneLine.toString());
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	private void writeCSVColumnHeaders(BufferedWriter bw) {
		StringBuffer oneLine = new StringBuffer();
		oneLine.append(OTS_NAME);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(FROM_NODE);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(TO_NODE);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(FROMNODE_PORT);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(TONODE_PORT);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(FROMNODE_PORT_2);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(TONODE_PORT_2);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(SHAPE);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(ASON_NPA);
		oneLine.append(CSV_SEPARATOR);
		oneLine.append(NUMBER_OF_CHANNELS);
		for (int x = 9130; x <= 9605; x = x + 5) {
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(x);
		}
		oneLine.append("\n");
		try {
			bw.flush();
			bw.write(oneLine.toString());
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

    private static String appendDQ(String str) {
        return "\"" + str + "\"";
    }


} // End of class
