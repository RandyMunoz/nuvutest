package com.nuvu.api.people.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDTO implements Serializable {

	private static final long serialVersionUID = -2070795038054173238L;

	private String access_token;
	private String token_type;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDTO(String access_token, String token_type, String username,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.access_token = access_token;
		this.token_type = token_type;
		this.username = username;
		this.authorities = authorities;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "JwtDTO [access_token=" + access_token + ", token_type=" + token_type + ", username=" + username
				+ ", authorities=" + authorities + "]";
	}

}
