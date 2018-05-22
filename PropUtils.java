

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropUtils {

	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("===========================================");
		System.out.println("===========================================");
		System.out.println("This application accept three parameters:");
		System.out.println("param1 : [OLD REFERENCE GRADLE PROPERTIES]reference old.gradle.properties");
		System.out.println("param2 : [GRADLE PROPERTIES] template empty.gradle.properties(Empty gradle.properties with no value set which comes with patch)");
		System.out.println("param3 :  gradle.properties with new values");
		System.out.println("===========================================");
		System.out.println("===========================================");
		
		System.out.println("To Run This Utilility type following comand ");
		System.out.println("java PropUtils \"path1\"  \"path2\"  \"path3\" ");
		System.out.println("example\njava PropUtils \"/opt/install/old.gradle.properties\" \"/opt/install/empty.gradle.properties\" \"/opt/install/gradle.properties\"  ");
		System.out.println("===========================================");
		System.out.println("===========================================");
		
//		args =  new String[3];
//		args[0] = "C:\\_delete\\proputils\\old.gradle.properties";
//		args[1] = "C:\\_delete\\proputils\\target.gradle.properties";
//		args[2] = "C:\\_delete\\proputils\\gradle.properties";
//		
		if(args == null || args.length < 3) {
			System.err.println("Invalid number of arguments.\n Exiting app....");
			try {
				Thread.sleep(10*1000);
			} catch (Exception e) {}
			System.exit(0);
		}
		
		for (int i = 0; i < 3; i++) {
			String arg =  args[i];
			File aFile = new File(arg);
			System.out.println("input file>>" + arg);
			if(aFile.exists() == false) {
				System.out.println("File '"+aFile.getAbsolutePath() + "' does not exist.\n Exiting app...");
				try {
					Thread.sleep(10*1000);
				} catch (Exception e) {}

				System.exit(0);
			}
		}
		
		System.out.println("[OLD GRADLE PROPERTIES] Reading old gradle properties (gradle.properties with old values)");
		Properties oldProperties = new Properties();
		try (FileInputStream in = new FileInputStream(args[0])){
			
			oldProperties.load(new FileInputStream(args[0]));
			
		} catch (Exception e) {
		}
		
	
		
		System.out.println("[ GRADLE PROPERTIES] Reading gradle.properties file(Empty gradle.properties with no value set)");
		Properties targetProperties = new Properties();
		try (FileInputStream in = new FileInputStream(args[1])){
			
			targetProperties.load(new FileInputStream(args[1]));
			
		} catch (Exception e) {
		}
		
		
		System.out.println("Reading new gradle properties with new values");
		Properties newProperties = new Properties();
		try (FileInputStream in = new FileInputStream(args[2])){
			
			newProperties.load(new FileInputStream(args[2]));
			
		} catch (Exception e) {
		}
		
		
		
		System.out.println("\n---------------------------");
		System.out.println("Checking for missing properties or empty value");
		System.out.println("---------------------------");
		
		Set allKeys = targetProperties.keySet();
		Set newKeys = newProperties.keySet();
		Set oldKeys = oldProperties.keySet();
		System.out.println("Total Number of properties:"+allKeys.size());
		
		
		
		
		System.out.println("Total Number of properties in new gradle.properties file:"+newKeys.size());
		
		for (Iterator iterator = allKeys.iterator(); iterator.hasNext();) {
			String aKey = (String) iterator.next();
			if(newKeys.contains(aKey) == false) {
				System.out.println("MISSING PROPERTY:" + aKey);
			}
		}
		

		
		System.out.println("\n---------------------------");
		System.out.println("Checking for Extra properties in gradle.properties");
		System.out.println("---------------------------");
		
		for (Iterator iterator = newKeys.iterator(); iterator.hasNext();) {
			String aKey = (String) iterator.next();
			if(allKeys.contains(aKey) == false) {
				System.out.println("EXTRA PROPERTY:" + aKey);
			}
		}
		
		
		
		
		
		System.out.println("\n---------------------------");
		System.out.println("Checking for properties with empty value");
		System.out.println("---------------------------");

		for (Iterator iterator = newKeys.iterator(); iterator.hasNext();) {
			String aKey = (String) iterator.next();
			String value = newProperties.getProperty(aKey);
			if(value == null || value.isEmpty()) {
				System.out.println("VALUE NOT SET FOR PROPERTY:: " + aKey);
			}
		}
		
		
		System.out.println("\n---------------------------");
		System.out.println("Checking for value mismatch with existing one");
		System.out.println("---------------------------");
		
		
		for (Iterator iterator = newKeys.iterator(); iterator.hasNext();) {
			String aKey = (String) iterator.next();
			String newValue = newProperties.getProperty(aKey);
			
			String oldValue = oldProperties.getProperty(aKey);
			
			if(oldValue != null) {
				if(oldValue.equals(newValue) == false) {
					System.out.println("VALUE OF PROPERTY IS DIFFERENT FROM OLD ONE:: " + aKey);
					System.out.println("OLD VALUE [" + oldValue +"] \nNEW VALUE [" + newValue +"]");
				}
			}
			
		}
		
	}
	
	
	
	
	
}
