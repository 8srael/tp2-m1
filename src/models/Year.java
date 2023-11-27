package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "years")
public class Year {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "label", nullable=false, length = 150)
	private String label;	
	
	@OneToMany(mappedBy = "year", fetch=FetchType.EAGER)
	private List<Group> groups;
	
	@OneToMany(mappedBy = "year", fetch = FetchType.EAGER)
	private List<Teacher_UE_Year> teacher_ues_years = new ArrayList<Teacher_UE_Year>();
	
	public Year() {}

	public Year(String label) {
		super();
		this.label = label;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public List<Teacher_UE_Year> getTeacher_UE_Years() {
		return teacher_ues_years;
	}
	
	
	@Override
	public String toString() {
		return this.label;
	}
}
