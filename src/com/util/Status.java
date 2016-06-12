package com.util;


public enum Status {
	NO,
	YES;
	@SuppressWarnings("unused")
	private String label;
	
	public static Status getStatus(int r){
		for(Status role : Status.values()){
			if(role.ordinal()==r){
				return role;
			}
		}
		return null;
	}

	public String getLabel() {

		   switch (this) {
			   case NO:
			    return "进货";
			   case YES:
			    return "退货";
		   }

		   return super.toString();
	}
}
