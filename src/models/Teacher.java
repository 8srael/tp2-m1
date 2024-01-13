package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the teachers database table.
 * 
 */
@Entity
@Table(name="teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name = "firstName", nullable = false, length = 150)
	private String firstName;
	
	@Column(name = "lastName", nullable = false, length = 250)
	private String lastName;
	
	@Column(name = "tel", nullable = false, length = 150)
	private String tel;
	
	@Column(name = "email", nullable = false, length = 250)
	private String email;
	
	@Column(name = "grade", nullable = false, length = 100)
	String grade;
	
	@OneToMany(mappedBy = "teacher")
	private List<Teacher_UE_Year> teacher_ues_years = new ArrayList<Teacher_UE_Year>();
	
	public Teacher() {}

	public long getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Teacher(String firstName, String lastName, String email, String tel, String grade) {
		this.email = email;
		this.firstName = firstName;
		this.grade = grade;
		this.lastName = lastName;
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public List<Teacher_UE_Year> getTeacher_UE_Years() {
		return teacher_ues_years;
	}

	public String toString() {
		return this.lastName + ' ' + this.firstName + " - " + this.grade;
	}
}