/**
 * 
 */
package com.daw.app.enumerados;

/**
 * @author Orlando Pasaca
 *
 * @since 2 nov. 2021
 */
public enum EnumEstadoCivil {
	S("S"), C("C"), V("V"), D("D");
	
	private final String value;
	EnumEstadoCivil(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
