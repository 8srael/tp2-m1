package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the ues database table.
 * 
 */
@Entity
@Table(name="ues")
public class UE  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String code;

	private String label;

	private int nHoursCM;

	private int nHoursTD;

	private int nHoursTP;
	
	@OneToMany(mappedBy = "ue", fetch = FetchType.EAGER)
	private List<Group> groups = new ArrayList<Group>();
	
	@OneToMany(mappedBy = "ue", fetch = FetchType.EAGER)
	private List<Teacher_UE_Year> teacher_ues_years = new ArrayList<Teacher_UE_Year>();
	
	
	public UE() {}

	public UE(String code, String label, int nHoursCM, int nHoursTD, int nHoursTP) {
		this.code = code;
		this.label = label;
		this.nHoursCM = nHoursCM;
		this.nHoursTD = nHoursTD;
		this.nHoursTP = nHoursTP;
	}
	
	public long getId() {
		return id;
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
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public List<Teacher_UE_Year> getTeacher_UE_Years() {
		return teacher_ues_years;
	}
	
	@Override
	public String toString() {
		return this.code + " - " + this.label;
	}
}