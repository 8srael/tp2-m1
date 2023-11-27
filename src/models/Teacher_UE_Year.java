package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teachers_ues_years")
public class Teacher_UE_Year {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "ue_id")
	UE ue;
	
	@ManyToOne
	@JoinColumn(name = "year_id")
	Year year;
	
	@Column(name = "nHoursCMAss")
	int nHoursCMAss;
	
	@Column(name = "nHoursTDAss")
	int nHoursTDAss;
	
	@Column(name = "nHoursTPAss")
	int nHoursTPAss;
	
	public Teacher_UE_Year() {}

	public Teacher_UE_Year(Teacher teacher, UE ue, Year year, int nHoursCMAss, int nHoursTDAss, int nHoursTPAss) {
		this.teacher = teacher;
		this.ue = ue;
		this.year = year;
		this.nHoursCMAss = nHoursCMAss;
		this.nHoursTDAss = nHoursTDAss;
		this.nHoursTPAss = nHoursTPAss;
	}

	public long getId() {
		return id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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

	public void setNHoursTPAss(int nHourssTPAss) {
		this.nHoursTPAss = nHourssTPAss;
	}
}
