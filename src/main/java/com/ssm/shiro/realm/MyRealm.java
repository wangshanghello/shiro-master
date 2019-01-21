package com.ssm.shiro.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.ssm.shiro.entity.Entity;
import com.ssm.shiro.serivce.SserivceImp;

public class MyRealm extends AuthorizingRealm {// 奥什瑞特瑞挺

	@Resource(name = "sserivce")
	private SserivceImp sserivce;

	// 为当前登录成功的用户授予权限和角色，已经登录成功了。
	// doGetAuthorizationInfo获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( // 昂服软的睿什
			PrincipalCollection principals) {
		// 1. 从 PrincipalCollection 中来获取登录用户的信息
		String username = (String) principals.getPrimaryPrincipal();
		System.out.println("username:" + username);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(sserivce.getRoles(username));
		authorizationInfo.setStringPermissions(sserivce
				.getPermissions(username));
		return authorizationInfo;
	}

	// 验证当前登录的用户，获取认证信息。
	// doGetAuthenticationInfo 获取身份验证相关信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( // 昂繁体凯盛
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();// 获取用户名

		Entity entity = sserivce.getByUsername(username);
		// 盐
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		// realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();

		if (entity != null) {
			SimpleAuthenticationInfo authcInfo = null; //

			authcInfo = new SimpleAuthenticationInfo(entity.getUsername(),
					entity.getPassword(), credentialsSalt, realmName);

			return authcInfo;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {

		String salt = "wang";
		String username = "123";
		// 加密
		String getUsername = new Md5Hash(username, salt).toString();
		System.out.println("getUsername:" + getUsername);
	}
}
