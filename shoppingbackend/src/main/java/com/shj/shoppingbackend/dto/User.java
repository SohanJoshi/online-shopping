package com.shj.shoppingbackend.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user_details")
public class User implements Serializable{

	/**
	 * version ID for Serialization
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="Please enter First Name")
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	@NotBlank(message="Please enter Last Name")
	private String lastName;
	
	@NotBlank(message="Please enter as email address")
	@Email(message="Please enter valid Email Address")
	private String email;
	
	@NotBlank(message="Please enter Contact Number")
	@Column(name="contact_number")
	private String contactNumber;
	
	private String role;
	
	//@Length(min=8, max=16, message="Password size is out of range (Please try password with [8-16] characters)")
	private String password;
	
	@NotBlank(message="Please re-enter Password")
	@Transient
	private String confirmPassword;
	
	@Column(name="is_enabled")
	private boolean enabled = true;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Cart cart;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				//+ ", contactNumber=" + contactNumber 
				+ ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	
}
