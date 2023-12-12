package utils;

import models.Teacher_UE_Year;

public class CombinedDataReview {
	private String ueLabel;
	private int nHoursCM;
	private int nHoursTD;
	private int nHoursTP;
	private Teacher_UE_Year tuy;
	
	public CombinedDataReview(String ueLabel, int nHoursCM, int nHoursTD, int nHoursTP, Teacher_UE_Year tuy) {
		this.ueLabel = ueLabel;
		this.nHoursCM = nHoursCM;
		this.nHoursTD = nHoursTD;
		this.nHoursTP = nHoursTP;
		this.tuy = tuy;
	}
	
	public Teacher_UE_Year getTuy() {
		return tuy;
	}

	public void setTuy(Teacher_UE_Year tuy) {
		this.tuy = tuy;
	}

	public CombinedDataReview() {}
	

	public String getUeLabel() {
		return ueLabel;
	}
	public void setUeLabel(String ueLabel) {
		this.ueLabel = ueLabel;
	}
	public int getNHoursCM() {
		return nHoursCM;
	}
	public void setNHoursCM(int nHoursCM) {
		this.nHoursCM = nHoursCM;
	}
	public int getNHoursTD() {
		return nHoursTD;
	}
	public void setNHoursTD(int nHoursTD) {
		this.nHoursTD = nHoursTD;
	}
	public int getNHoursTP() {
		return nHoursTP;
	}
	public void setNHoursTP(int nHoursTP) {
		this.nHoursTP = nHoursTP;
	}
	
	

}
