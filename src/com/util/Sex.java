package com.util;

public enum Sex {
	WOMAN,
	MAN;
	@SuppressWarnings("unused")
	private String label;
	
	public String getLabel() {

		   switch (this) {
			   case WOMAN:
			    return "女";
			   case MAN:
			    return "男";
		   }

		   return super.toString();
	}
}
