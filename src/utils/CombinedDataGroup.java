package utils;


public class CombinedDataGroup {
    private String code;
    private String label;
    private int nHoursCM;
	private int nHoursTD;
	private int nHoursTP;
    private int nGroupsCM;
    private int nGroupsTD;
    private int nGroupsTP;

    
    public CombinedDataGroup() {}
    
    public CombinedDataGroup(String code, String label, int nHoursCM, int nHoursTD, int nHoursTP, int nGroupsCM, int nGroupsTD, int nGroupsTP) {
        this.code = code;
        this.label = label;
        this.nHoursCM = nHoursCM;
        this.nHoursTD = nHoursTD;
        this.nHoursTP = nHoursTP;
        this.nGroupsCM = nGroupsCM;
        this.nGroupsTD = nGroupsTD;
        this.nGroupsTP = nGroupsTP;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public int getNGroupsCM() {
		return nGroupsCM;
	}

	public void setNGroupsCM(int nGroupsCM) {
		this.nGroupsCM = nGroupsCM;
	}

	public int getNGroupsTD() {
		return nGroupsTD;
	}

	public void setNGroupsTD(int nGroupsTD) {
		this.nGroupsTD = nGroupsTD;
	}

	public int getNGroupsTP() {
		return nGroupsTP;
	}

	public void setNGroupsTP(int nGroupsTP) {
		this.nGroupsTP = nGroupsTP;
	}
	
	
}
