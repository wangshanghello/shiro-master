package com.ssm.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm.shiro.entity.Entity;
/**
 * 
 *  @author wangshang
 * 	@date 2018年6月19日/下午3:16:28  
 * 	@类作用: controller层
 */
@Controller
@RequestMapping("/user") 
public class Scontroller {
	
	  //用户登录  
    @RequestMapping("/login")  
    public String login(Entity user, HttpServletRequest request){  
       
        try {  
            //调用subject.login(token)进行登录，会自动委托给securityManager,调用之前 
        	if(user.getUsername()!=null&&!"".equals(user.getUsername())){
        		 Subject subject=SecurityUtils.getSubject();  
        	        ByteSource salt =  ByteSource.Util.bytes(user.getUsername());
        			//加密
        			String getpassword = new Md5Hash(user.getPassword(), salt).toString();
        	        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),getpassword);  
        		 subject.login(token);//会跳到我们自定义的realm中  
                 request.getSession().setAttribute("user",user);  
                 return "success";  
        	}
        	return "redirect:/login.jsp";
        } catch (AuthenticationException ae) {
            //unexpected condition?  error?
        	System.out.println("登录失败: " + ae.getMessage());
        	return "redirect:/login.jsp";
        }
        
    }  
	
	   @RequestMapping("/logout")  
	    public String logout(HttpServletRequest request){  
	        request.getSession().invalidate();  
	        return "login";  
	    } 
	   /**
	    * 测试没有授权登录   index能否直接访问
	    * @param request
	    * @return
	    */
	   @RequestMapping("/index")  
	    public String index(HttpServletRequest request){  
	       
	        return "index";  
	    } 
}
