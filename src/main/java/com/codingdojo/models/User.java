package com.codingdojo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private int user_type;
	
	@NotNull
    @Size(max = 100)
	private String first_name;
    
	@NotNull
    @Size(max = 100)
    private String last_name;
	
	@NotNull
    @Size(max = 200)
    private String email;
	
	@NotNull
    @Size(max = 255)
    private String address;
	
	@NotNull
    @Size(max = 100)
    private String city;
	
	@NotNull
    @Size(max = 100)
    private String state;
	
	@NotNull
    private int zipcode;
    
	@NotNull
    @Size(max = 200)
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created_at;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updated_at;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Cloth> cloth;
    
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "current_orders", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "cloth_id")
	)
	private List<Cloth> clothesInCurrentOrder;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "past_orders", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "current_order_id")
	)
	private List<CurrentOrder> pastCurrent_orders;
    
	
    public User() {
    }
    
    public User( String first_name, String last_name, String email, String address, String state,  String city, int zipcode, String password ) {
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.email = email;
    	this.address = address;
    	this.state = state;
    	this.city = city;
    	this.zipcode = zipcode;
    	this.password = password;
    }
    
    public User( int user_type, String first_name, String last_name, String email, String address, String state,  String city, int zipcode, String password, List<Cloth> cloth, List<Cloth> clothesInCurrentOrder, List<CurrentOrder> pastCurrent_orders ) {
    	this.user_type = user_type;
    	this.first_name = first_name;
    	this.last_name = last_name;
    	this.email = email;
    	this.address = address;
    	this.state = state;
    	this.city = city;
    	this.zipcode = zipcode;
    	this.password = password;
    	this.cloth = cloth;
    	this.clothesInCurrentOrder = clothesInCurrentOrder;
    	this.pastCurrent_orders = pastCurrent_orders;
    	
    }
	
    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    
	@PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<Cloth> getCloth() {
		return cloth;
	}

	public void setCloth(List<Cloth> cloth) {
		this.cloth = cloth;
	}

	public List<Cloth> getClothesInCurrentOrder() {
		return clothesInCurrentOrder;
	}

	public void setClothesInCurrentOrder(List<Cloth> clothesInCurrentOrder) {
		this.clothesInCurrentOrder = clothesInCurrentOrder;
	}

	public List<CurrentOrder> getPastCurrent_orders() {
		return pastCurrent_orders;
	}

	public void setPastCurrent_orders(List<CurrentOrder> pastCurrent_orders) {
		this.pastCurrent_orders = pastCurrent_orders;
	}



	
	
	
}
