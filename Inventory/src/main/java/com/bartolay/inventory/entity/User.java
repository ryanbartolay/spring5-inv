package com.bartolay.inventory.entity;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.model.Authority;

@Entity
@Table(name = "users")
public class User implements Serializable, Principal {
 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "USER_SER_SEQ")
    private long id;
    @Column(name = "firstname", nullable=false)
    private String firstName;
    @Column(name = "lastname", nullable=false)
    private String lastName;
    @Column(name = "username", nullable=false, unique=true)
    private String username;
    @Column(name = "password", nullable=false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "type", nullable=false, insertable = true)
    private String type;
    @Column(name = "enabled", nullable=false)
    private boolean enabled;
    
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;
	
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

    public User(long id, String firstName, String lastName, 
            String userName, String password, String phone, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public User(long id, String firstName, String lastName, 
            String userName, String password, String phone, String address, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.type = type;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + username
				+ ", password=" + password + ", email=" + email + ", phone=" + phone + ", address=" + address + ", type=" + type
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

}
