/**
 * 
 */
package com.daw.app.enumerados;

/**
 * @author Orlando Pasaca
 *
 * @since 2 nov. 2021
 */
public enum EnumSexo {
	M("M") , F("F");
	
	private final String value;
	
	EnumSexo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	
}
