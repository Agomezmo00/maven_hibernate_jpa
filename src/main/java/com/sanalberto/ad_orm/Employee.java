package com.sanalberto.ad_orm;
// Generated 11 ene. 2021 21:27:48 by Hibernate Tools 5.2.12.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "Employee", catalog = "chinook")
public class Employee implements java.io.Serializable {

	private Integer employeeId;
	private Employee employee;
	private String lastName;
	private String firstName;
	private String title;
	private Date birthDate;
	private Date hireDate;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String phone;
	private String fax;
	private String email;
	private Set<Employee> employees = new HashSet<Employee>(0);
	private Set<Customer> customers = new HashSet<Customer>(0);

	public Employee() {
	}

	public Employee(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public Employee(Employee employee, String lastName, String firstName, String title, Date birthDate, Date hireDate,
			String address, String city, String state, String country, String postalCode, String phone, String fax,
			String email, Set<Employee> employees, Set<Customer> customers) {
		this.employee = employee;
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.employees = employees;
		this.customers = customers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "EmployeeId", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ReportsTo")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "LastName", nullable = false, length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "FirstName", nullable = false, length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "Title", length = 30)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BirthDate", length = 19)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HireDate", length = 19)
	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Column(name = "Address", length = 70)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "City", length = 40)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "State", length = 40)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "Country", length = 40)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "PostalCode", length = 10)
	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Column(name = "Phone", length = 24)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Fax", length = 24)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "Email", length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

}
