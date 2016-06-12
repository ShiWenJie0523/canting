package com.dao;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.bean.UserBean;
import com.bean.UserLoginDetails;

public class UserLoginServiceImpl implements UserDetailsService {
	private UserDao userDao;
	
	// 根据用户名返回用户信息
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserBean user = userDao.findUserByName(username);
		UserLoginDetails userDetails = new UserLoginDetails();  
		if(user!=null){
			userDetails.setUsername(user.getUsername()); // 设置用户名  
		    userDetails.setPassword(user.getUserpwd()); // 设置密码  
		    userDetails.setEnabled(Boolean.parseBoolean(user.getStatus())); // 设置启用状态
            // 角色字符串如：”ROLE_SUPERVISOR，ROLE_USER”。以逗号隔开  
            String[] rights = user.getRole().split(","); // 分割多个角色
            // 设置用户的授权信息  
            GrantedAuthority[] authorities = new GrantedAuthority[rights.length];                   
            for (int i = 0; i < rights.length; i++) {  
                authorities[i] = new GrantedAuthorityImpl(rights[i]);  
            }  
            userDetails.setAuthorities(authorities);
		}else { // 如果该用户不存在，则抛出异常
            throw new UsernameNotFoundException("User not found");  
        }  
		return userDetails;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
}
