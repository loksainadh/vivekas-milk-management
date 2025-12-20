package com.milk.vivekas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "providers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactNumber;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MilkRecord> milkRecords;
    
 // One Provider ↔ One ProviderProfile
    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL)
    @JsonIgnore
    private ProviderProfile profile;


    public ProviderProfile getProfile() {
		return profile;
	}

	public void setProfile(ProviderProfile profile) {
		this.profile = profile;
	}

	public Provider() {}

    public Provider(Long id, String name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public List<User> getUsers() { return users; }
    public void setUsers(List<User> users) { this.users = users; }

    public List<MilkRecord> getMilkRecords() { return milkRecords; }
    public void setMilkRecords(List<MilkRecord> milkRecords) { this.milkRecords = milkRecords; }
}
