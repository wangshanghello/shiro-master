package com.ssm.shiro.serivce;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.shiro.dao.Sdao;
import com.ssm.shiro.entity.Entity;

@Service("sserivce")
public class SserivceImp implements Sserivce {

	@Resource(name = "sdao")
	private Sdao sdao;

	@Override
	public Entity getByUsername(Object getUsername) {
		// TODO Auto-generated method stub
		return sdao.getByUsername(getUsername);
	}

	@Override
	public Set<String> getRoles(String username) {
		// TODO Auto-generated method stub
		return sdao.getRoles(username);
	}

	@Override
	public Set<String> getPermissions(String username) {
		// TODO Auto-generated method stub
		return sdao.getPermissions(username);
	}

}
