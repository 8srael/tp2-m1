package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "ue_id")
	UE ue;
	
	@ManyToOne
	@JoinColumn(name = "year_id")
	Year year;
	
	@Column(name = "nGroupsCM")
	int nGroupsCM;
	
	@Column(name = "nGroupsTD")
	int nGroupsTD;
	
	@Column(name = "nGroupsTP")
	int nGroupsTP;
	
	
	public Group() {}
	
	public Group(UE ue, Year year, int nGroupsCM, int nGroupsTD, int nGroupsTP) {
		this.ue = ue;
		this.year = year;
		this.nGroupsCM = nGroupsCM;
		this.nGroupsTD = nGroupsTD;
		this.nGroupsTP = nGroupsTP;
	}

	public long getId() {
		return id;
	}

	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public int getnGroupsTD() {
		return nGroupsTD;
	}

	public void setnGroupsTD(int nGroupsTD) {
		this.nGroupsTD = nGroupsTD;
	}

	public int getnGroupsTP() {
		return nGroupsTP;
	}

	public void setnGroupsTP(int nGroupsTP) {
		this.nGroupsTP = nGroupsTP;
	}

	public int getnGroupsCM() {
		return nGroupsCM;
	}

	public void setnGroupsCM(int nGroupsCM) {
		this.nGroupsCM = nGroupsCM;
	}

}
