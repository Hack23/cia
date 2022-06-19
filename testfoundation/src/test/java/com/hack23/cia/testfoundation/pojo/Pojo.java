package com.hack23.cia.testfoundation.pojo;

import java.util.Objects;

public class Pojo {

	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public int hashCode() {
		return Objects.hash(string);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pojo other = (Pojo) obj;
		return Objects.equals(string, other.string);
	}

	@Override
	public String toString() {
		return "Pojo [string=" + string + "]";
	}
	
	
}
