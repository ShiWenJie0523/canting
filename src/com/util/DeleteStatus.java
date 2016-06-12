package com.util;

public enum DeleteStatus {
	NO,
	YES;
	
	@SuppressWarnings("unused")
	private String label;
	
	public static DeleteStatus getDeleteStatus(int r){
		for(DeleteStatus role : DeleteStatus.values()){
			if(role.ordinal()==r){
				return role;
			}
		}
		return null;
	}

	public String getLabel() {

		   switch (this) {
			   case NO:
			    return "不删除";
			   case YES:
			    return "已删除";
		   }

		   return super.toString();
	}
}
