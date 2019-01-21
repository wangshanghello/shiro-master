package com.ssm.shiro.serivce;

import java.util.Set;

import com.ssm.shiro.entity.Entity;

public interface Sserivce {
	public Entity getByUsername(Object getUsername);

	public Set<String> getRoles(String username);

	public Set<String> getPermissions(String username);
}
