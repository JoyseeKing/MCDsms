package mcd.util;

import java.util.UUID;

public class UIDUtil {
	public String RadomID() {
		UUID uid=UUID.randomUUID(); 
		String i=uid.toString().replace("-", "");
		return i;
	}
}
