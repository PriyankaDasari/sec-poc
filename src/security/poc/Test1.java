package security.poc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test1 {

	public static void main(String[] args) {
		 Map<String, String> userData = new HashMap<>();
		   StringBuffer roleSB = new StringBuffer("");
	        Set<String> roles = new HashSet<>();
	        roles.add("Everyone");
	        roles.add("afs.salesadmin");
	        roles.remove("Everyone");
	        String role = roles.toString();
	        System.out.println(role);
	        	
	}

}
