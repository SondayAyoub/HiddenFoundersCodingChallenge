package com.HFTest.entities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document
@JsonIgnoreProperties
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@NotNull
	@NotEmpty(message="Email should not be empty!")
	@Email(message="Invalid email address!!")
	private String email;
	
	@NotNull
	@NotEmpty(message="Password should not be empty!")
	@Size(min=6, message="Password must be at least 6chars!")
	private String password;
	
	private Date creationDate;
	private Date lastAccessDate;
	private boolean enabled;
	private boolean tokenExpired;
	
	private Set<Role> roles;
	private Set<Shops> favShops;
	
	public Set<Shops> getFavShops() {
		return favShops;
	}

	public void setFavShops(Set<Shops> favShops) {
		this.favShops = favShops;
	}

	private List<GrantedAuthority> authorithies;

	public List<GrantedAuthority> getAuthorithies() {
		return authorithies;
	}

	public void setAuthorithies(List<GrantedAuthority> authorithies) {
		this.authorithies = authorithies;
	}

	public User() {
		super();
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.creationDate = new Date();
		this.enabled = true;
		this.tokenExpired = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [email=" + email + ", creationDate=" + creationDate + ", lastAccessDate=" + lastAccessDate
				+ ", enabled=" + enabled + ", roles=" + roles + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<Role> r = getRoles();
		if (r != null)
			authorities.addAll(r);
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}