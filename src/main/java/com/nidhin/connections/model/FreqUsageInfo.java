package com.nidhin.connections.model;


import java.util.HashMap;
import java.util.LinkedHashMap;

public class FreqUsageInfo  {

	private String otsName;
	private Long otsotnId;
	private String otsDirection;
	private String otsShape;
	private Long omsotnId;
	private String fromNode1;
	private String toNode1;
	private String fromPort1;
	private String toPort1;
	private String fromPort2;
	private String toPort2;
	private String fromNode2;
	private String toNode2;
	private Integer channelSize;
	private String asonNPA;

	public  LinkedHashMap<Integer,Object> frequencyMap = new LinkedHashMap<Integer,Object>(){
		{
			put(9130,"n/a");
			put(9135,"n/a");
			put(9140,"n/a");
			put(9145,"n/a");
			put(9150,"n/a");
			put(9155,"n/a");
			put(9160,"n/a");
			put(9165,"n/a");
			put(9170,"n/a");
			put(9175,"n/a");
			put(9180,"n/a");
			put(9185,"n/a");
			put(9190,"n/a");
			put(9195,"n/a");
			put(9200,"n/a");
			put(9205,"n/a");
			put(9210,"n/a");
			put(9215,"n/a");
			put(9220,"n/a");
			put(9225,"n/a");
			put(9230,"n/a");
			put(9235,"n/a");
			put(9240,"n/a");
			put(9245,"n/a");
			put(9250,"n/a");
			put(9255,"n/a");
			put(9260,"n/a");
			put(9265,"n/a");
			put(9270,"n/a");
			put(9275,"n/a");
			put(9280,"n/a");
			put(9285,"n/a");
			put(9290,"n/a");
			put(9295,"n/a");
			put(9300,"n/a");
			put(9305,"n/a");
			put(9310,"n/a");
			put(9315,"n/a");
			put(9320,"n/a");
			put(9325,"n/a");
			put(9330,"n/a");
			put(9335,"n/a");
			put(9340,"n/a");
			put(9345,"n/a");
			put(9350,"n/a");
			put(9355,"n/a");
			put(9360,"n/a");
			put(9365,"n/a");
			put(9370,"n/a");
			put(9375,"n/a");
			put(9380,"n/a");
			put(9385,"n/a");
			put(9390,"n/a");
			put(9395,"n/a");
			put(9400,"n/a");
			put(9405,"n/a");
			put(9410,"n/a");
			put(9415,"n/a");
			put(9420,"n/a");
			put(9425,"n/a");
			put(9430,"n/a");
			put(9435,"n/a");
			put(9440,"n/a");
			put(9445,"n/a");
			put(9450,"n/a");
			put(9455,"n/a");
			put(9460,"n/a");
			put(9465,"n/a");
			put(9470,"n/a");
			put(9475,"n/a");
			put(9480,"n/a");
			put(9485,"n/a");
			put(9490,"n/a");
			put(9495,"n/a");
			put(9500,"n/a");
			put(9505,"n/a");
			put(9510,"n/a");
			put(9515,"n/a");
			put(9520,"n/a");
			put(9525,"n/a");
			put(9530,"n/a");
			put(9535,"n/a");
			put(9540,"n/a");
			put(9545,"n/a");
			put(9550,"n/a");
			put(9555,"n/a");
			put(9560,"n/a");
			put(9565,"n/a");
			put(9570,"n/a");
			put(9575,"n/a");
			put(9580,"n/a");
			put(9585,"n/a");
			put(9590,"n/a");
			put(9595,"n/a");
			put(9600,"n/a");
			put(9605,"n/a");
		}
		};
	public String getOtsName() {
		return otsName;
	}
	public void setOtsName(String otsName) {
		this.otsName = otsName;
	}
	public String getFromNode1() {
		return fromNode1;
	}
	public void setFromNode1(String fromNode1) {
		this.fromNode1 = fromNode1;
	}
	public String getToNode1() {
		return toNode1;
	}
	public void setToNode1(String toNode1) {
		this.toNode1 = toNode1;
	}
	public String getFromNode2() {
		return fromNode2;
	}
	public void setFromNode2(String fromNode2) {
		this.fromNode2 = fromNode2;
	}
	public String getToNode2() {
		return toNode2;
	}
	public void setToNode2(String toNode2) {
		this.toNode2 = toNode2;
	}
	public Integer getChannelSize() {
		return channelSize;
	}
	public void setChannelSize(Integer channelSize) {
		this.channelSize = channelSize;
	}
	public HashMap<Integer, Object> getFrequencyMap() {
		return frequencyMap;
	}
	public void setFrequencyMap(LinkedHashMap<Integer, Object> frequencyMap) {
		this.frequencyMap = frequencyMap;
	}
	public String getAsonNPA() {
		return asonNPA;
	}
	public void setAsonNPA(String asonNPA) {
		this.asonNPA = asonNPA;
	}
	public Long getOtsotnId() {
		return otsotnId;
	}
	public void setOtsotnId(Long otsotnId) {
		this.otsotnId = otsotnId;
	}
	public Long getOmsotnId() {
		return omsotnId;
	}
	public void setOmsotnId(Long omsotnId) {
		this.omsotnId = omsotnId;
	}
	public String getOtsDirection() {
		return otsDirection;
	}
	public void setOtsDirection(String otsDirection) {
		this.otsDirection = otsDirection;
	}
	public String getOtsShape() {
		return otsShape;
	}
	public void setOtsShape(String otsShape) {
		this.otsShape = otsShape;
	}
	public String getFromPort1() {
		return fromPort1;
	}
	public void setFromPort1(String fromPort1) {
		this.fromPort1 = fromPort1;
	}
	public String getToPort1() {
		return toPort1;
	}
	public void setToPort1(String toPort1) {
		this.toPort1 = toPort1;
	}
	public String getFromPort2() {
		return fromPort2;
	}
	public void setFromPort2(String fromPort2) {
		this.fromPort2 = fromPort2;
	}
	public String getToPort2() {
		return toPort2;
	}
	public void setToPort2(String toPort2) {
		this.toPort2 = toPort2;
	}

}
