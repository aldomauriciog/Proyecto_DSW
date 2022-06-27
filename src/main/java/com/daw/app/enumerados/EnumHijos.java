package com.daw.app.enumerados;

public enum EnumHijos {
	SI("SI"), NO("NO");
	
	private final String value;
	EnumHijos(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}