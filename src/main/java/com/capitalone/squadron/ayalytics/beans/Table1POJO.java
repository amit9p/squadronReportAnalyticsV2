package com.capitalone.squadron.ayalytics.beans;

/*This pojo has repert metadata */
public class Table1POJO {

	private Integer reportID;
	private Integer frequency;
	private String  stopFlag;

	public String getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}

	public Integer getReportID() {
		return reportID;
	}

	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Table1POJO(Integer reportID, Integer frequency) {
		super();
		this.reportID = reportID;
		this.frequency = frequency;
	}

	public Table1POJO() {
	}

	@Override
	public String toString() {
		return "Table1POJO [reportID=" + reportID + ", frequency=" + frequency
				+ ", stopFlag=" + stopFlag + "]";
	}

	

}
