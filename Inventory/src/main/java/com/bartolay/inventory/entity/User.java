package com.bartolay.inventory.entity;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.model.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User implements Serializable, Principal {
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "USER_SER_SEQ")
    private int id;
    @Column(name = "firstname", nullable=false)
    private String firstName;
    @Column(name = "lastname", nullable=false)
    private String lastName;
    @Column(name = "username", nullable=false, unique=true)
    private String username;
    @JsonIgnore
    @Column(name = "password", nullable=false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_zipcode")
    private int addressZipcode;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_country_id", nullable=false, updatable=true)
    private Country addressCountry;
	
	@Deprecated
    @Column(name = "account_type", nullable=false, insertable = true)
    private AccountType accountType;  
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_group_id", nullable=true, updatable=true)
	private UserGroup userGroup;
    
    @Column(name = "enabled", nullable=false)
    private boolean enabled;
    
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=true, updatable=false)
	private User createdBy;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable=true, updatable=true)
	private User updatedBy;
	
    @Transient
    private List<Authority> authorities;
    
    private String authority;
  
    public User(){
    
    }

    public User(String firstName, String lastName, String userName, String password, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(int id, String firstName, String lastName, 
            String userName, String password, String phone, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(int id, String firstName, String lastName, 
            String userName, String password, String phone, String address, AccountType account_type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.accountType = account_type;
    }
    
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
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Deprecated
	public AccountType getAccountType() {
		return accountType;
	}

    @Deprecated
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	

	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDated() {
		return updatedDated;
	}

	public void setUpdatedDated(Date updatedDated) {
		this.updatedDated = updatedDated;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public int getAddressZipcode() {
		return addressZipcode;
	}

	public void setAddressZipcode(int addressZipcode) {
		this.addressZipcode = addressZipcode;
	}
	
	public Country getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Country addressCountry) {
		this.addressCountry = addressCountry;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + username
				+ ", password=" + password + ", email=" + email + ", phone=" + phone + ", address=" + address + ", accountType=" + accountType
				+ ", enabled=" + enabled + ", authority=" + authority + "]";
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getName() {
		return this.username;
	}

//	public UserGroup getUserGroup() {
//		return userGroup;
//	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
