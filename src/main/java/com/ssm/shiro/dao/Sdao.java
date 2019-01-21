package com.ssm.shiro.dao;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ssm.shiro.entity.Entity;

@Repository(value="sdao")
public interface Sdao {

	public Entity getByUsername(Object getUsername);

	public Set<String> getRoles(String username);

	public Set<String> getPermissions(String username);
}
