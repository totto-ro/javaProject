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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="clothes")
public class Cloth {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private int category;
	
	@NotNull
    @Size(max = 100)
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	private int price;
	
    @Column(updatable=false)
    private Date created_at;
    private Date updated_at;
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "current_orders", 
        joinColumns = @JoinColumn(name = "cloth_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> usersCurrentOrder;
	
	public Cloth() {
		
	}
	
	public Cloth(int category, String name, String description, int price) {
		this.category = category;
    	this.name = name;
    	this.description = description;
    	this.price = price;
		
	}
	
	public Cloth(int category, String name, String description, int price, User user, List<User> usersCurrentOrder) {
		this.category = category;
    	this.name = name;
    	this.description = description;
    	this.price = price;
    	this.user =user;
    	this.usersCurrentOrder = usersCurrentOrder;
		
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

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsersCurrentOrder() {
		return usersCurrentOrder;
	}

	public void setUsersCurrentOrder(List<User> usersCurrentOrder) {
		this.usersCurrentOrder = usersCurrentOrder;
	}





	
	
	
	
}//END
