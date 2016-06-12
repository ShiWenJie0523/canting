package com.bean;

public enum Role {
	/**管理员**/
	ROLE_ROOT,
	/**收银员**/
	ROLE_CASH;
	
	public static String getRole(int r){
		for(Role role : Role.values()){
			if(role.ordinal()==r){
				return role.toString();
			}
		}
		return null;
	}

}
