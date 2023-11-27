package utils;

public class CombinedDataAttribution {
	
	private String fullName;
	private int nHoursCMAss;
	private int nHoursTDAss;
	int nHoursTPAss;

	public CombinedDataAttribution() {}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String firstName, String lastName) {
		this.fullName = firstName + ' ' + lastName;
	}

	public int getNHoursCMAss() {
		return nHoursCMAss;
	}

	public void setNHoursCMAss(int nHoursCMAss) {
		this.nHoursCMAss = nHoursCMAss;
	}

	public int getNHoursTDAss() {
		return nHoursTDAss;
	}

	public void setNHoursTDAss(int nHoursTDAss) {
		this.nHoursTDAss = nHoursTDAss;
	}

	public int getNHoursTPAss() {
		return nHoursTPAss;
	}

	public void setNHoursTPAss(int nHoursTPAss) {
		this.nHoursTPAss = nHoursTPAss;
	}
	
	
}
