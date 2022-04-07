package reto;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AskFor {
	
	public static String brand() {
		
		String brand;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The brand must have 2-45 characters!");
			}
			System.out.println("\nEnter brand:");
			brand = Console.readString();
			if(brand.length() > 45 || brand.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return brand;
	}
	
	public static String model() {
		
		String model;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The model must have 2-45 characters!");
			}
			System.out.println("\nEnter model:");
			model = Console.readString();
			if(model.length() > 45 || model.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return model;
	}
	
	public static int year() {
		
		int year;
		String yearString;
		boolean correct = true;
		do {
			Calendar instance = Calendar.getInstance();
	        int currentYear = instance.get(Calendar.YEAR);
			if(correct == false) {
				System.out.println("*The year must be between 1900 and " + currentYear + "!");
			}
			System.out.println("\nEnter year:");
			yearString = Console.readString();
			
			while(!yearString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter year:");
				yearString = Console.readString();
			}
			year = Integer.parseInt(yearString);
			 
			if(year > currentYear || year < 1900) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return year;
	}

	public static String registration() {
		
		String registration;
		boolean exists = false;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The registration must have 4-10 characters");
			}else if(exists == true) {
				System.out.println("*The registration allready exists!");
			}
			System.out.println("\nEnter registration:");
			registration = Console.readString();
			
			ConnectionToDB myConnectionToDB = null;
			
			try {
				myConnectionToDB = new ConnectionToDB();
				ResultSet myResultSetRegistration = myConnectionToDB.myQuery("SELECT numFrame FROM vehicle WHERE UPPER(registration) = '" + registration.toUpperCase() + "'");
				if (myResultSetRegistration.next()) { 
					exists = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				if(myConnectionToDB != null){
					try{
						myConnectionToDB.disconnect();
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			
			if(registration.length() > 10 || registration.length() < 4) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false || exists == true);
		
		return registration;
	}
	
	public static String numFrame() {
		
		String numFrame;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The frame number must have 17 characters!");
			}
			System.out.println("\nEnter frame number:");
			numFrame = Console.readString();
			if(numFrame.length() != 17) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return numFrame;
	}
	
	public static String colour() {
		
		String colour;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The colour must have 3-45 characters!");
			}
			System.out.println("\nEnter colour:");
			colour = Console.readString();
			if(colour.length() > 45 || colour.length() < 3) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return colour;
	}

	public static int numOfSeats() {
		
		int numOfSeats;
		String numOfSeatsString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The seat number must be between 1 and 9!");
			}
			System.out.println("\nEnter seat number:");
			numOfSeatsString = Console.readString();
			
			while(!numOfSeatsString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter seat number:");
				numOfSeatsString = Console.readString();
			}
			numOfSeats = Integer.parseInt(numOfSeatsString);
			
			if(numOfSeats > 9 || numOfSeats < 1) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return numOfSeats;
	}
	
	public static int price() {
		
		int price;
		String priceString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The price must be between 0 and 3.000.000!");
			}
			System.out.println("\nEnter price (\u20ac):");
			priceString = Console.readString();
			
			while(!priceString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter price (\u20ac):");
				priceString = Console.readString();
			}
			price = Integer.parseInt(priceString);

			if(price > 3000000 || price < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);	
		
		return price;
	}
	
	public static int numDoors() {
		
		int numDoors;
		String numDoorsString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The door number must be between 2 and 5!");
			}
			System.out.println("\nEnter door number:");
			numDoorsString = Console.readString();
			
			while(!numDoorsString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter door number:");
				numDoorsString = Console.readString();
			}
			numDoors = Integer.parseInt(numDoorsString);
			
			if(numDoors > 5 || numDoors < 2) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return numDoors;
	}
	
	public static int trunkCapacity() {
		
		int trunkCapacity;
		String trunkCapacityString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The trunk capacity must be between 0 and 5000!");
			}
			System.out.println("\nEnter trunk capacity (l):");
			trunkCapacityString = Console.readString();
			
			while(!trunkCapacityString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter trunk capacity (l):");
				trunkCapacityString = Console.readString();
			}
			trunkCapacity = Integer.parseInt(trunkCapacityString);
			
			if(trunkCapacity > 5000 || trunkCapacity < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return trunkCapacity;
	}
	
	public static int load() {
		
		int load;
		String loadString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*The load must be between 0 and 40000!");
			}
			System.out.println("\nEnter load (kg):");
			loadString = Console.readString();
			
			while(!loadString.matches("[0-9]+")){
				System.out.println("*Only numbers!");
				System.out.println("\nEnter load (kg):");
				loadString = Console.readString();
			}
			load = Integer.parseInt(loadString);

			if(load > 40000 || load < 0) {
				correct = false;
			}else {
				correct = true;
			}
		} while (correct == false);
		
		return load;
	}
	
	public static char merchandiseType() {
		
		char merchandiseType;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 'g', 'a' or 'd'!");
			}
			System.out.println("\nEnter merchandise type: ('g' for general, 'a' for arid or 'd' for dangerous)");
			merchandiseType = Console.readChar();
			if(Character.toUpperCase(merchandiseType) == 'G' || Character.toUpperCase(merchandiseType) == 'A' || Character.toUpperCase(merchandiseType) == 'D') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);
		
		return merchandiseType;
	}
	
	public static char carOrTruck() {
		
		char answer;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 'c' or 't'!");
			}
			System.out.println("\nEnter vehicle kind: ('c' for car or 't' for truck)");
			answer = Console.readChar();
			if(Character.toLowerCase(answer) == 'c' || Character.toLowerCase(answer) == 't') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);	
		
		return answer;
	}
	
	public static char sameOrNewColor() {
		
		char answer;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 's' or 'n'!");
			}
			System.out.println("\nEnter painting option: ('s' for same colour or 'n' for a new one)");
			answer = Console.readChar();
			if(Character.toLowerCase(answer) == 's' || Character.toLowerCase(answer) == 'n') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);	
		
		return answer;
	}
	
	public static char allOrColour() {
		
		char answer;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 'a' or 'c'!");
			}
			System.out.println("\nEnter showing option: ('a' for showing all vehicles or 'c' to enter a color filter)");
			answer = Console.readChar();
			if(Character.toLowerCase(answer) == 'a' || Character.toLowerCase(answer) == 'c') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);	
		
		return answer;
	}
	
	public static char allSeriesOrThisVehicle() {
		
		char answer;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter 'a' or 'o'!");
			}
			System.out.println("\n*There are many vehicles of this series!\n\nEnter 'a' for modifying data of all the vehicles of the same series or 'o' for only this vehicle:");
			answer = Console.readChar();
			if(Character.toLowerCase(answer) == 'a' || Character.toLowerCase(answer) == 'o') {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);	
		
		return answer;
	}
	
	public static String fileName(){
		
		String fileName;
		boolean correct = true;
		boolean specialChars = false;
		do {
			if(correct == false) {
				System.out.println("*The file name must have 1-45 characters!");
			}else if(specialChars == true) {
				System.out.println("*No especial characters!");
			}
			System.out.println("\nEnter file name (without extension):");
			fileName = Console.readString();
			if(fileName.length() > 45 || fileName.length() < 2) {
				correct = false;
			}else {
				correct = true;
			}
			Pattern p = Pattern.compile("[^A-Za-z0-9_\\-]");
		    Matcher m = p.matcher(fileName);
		    if(m.find()) {
		    	specialChars = true;
		    }else {
		    	specialChars = false;
		    }
		} while (correct == false || specialChars == true);
		
		return fileName;
	}

	public static String initialDate() {
		
		String initialDateString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*YYYY-MM-DD!");
			}
			System.out.println("\nEnter initial date (YYYY-MM-DD):");
			initialDateString = Console.readString();
			if(checkDateFormat(initialDateString)) {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);

		return initialDateString;
	}
	
	public static String finalDate() {
		
		String finalDateString;
		boolean correct = true;
		do {
			if(correct == false) {
				System.out.println("*Enter YYYY-MM-DD!");
			}
			System.out.println("\nEnter final date (YYYY-MM-DD):");
			finalDateString = Console.readString();
			if(checkDateFormat(finalDateString)) {
				correct = true;
			}else {
				correct = false;
			}
		} while (correct == false);

		return finalDateString;
	}
	
	public static boolean checkDateFormat(String strDate) {
		
		if (strDate.trim().equals("")) {
		    return false;
		} else {

		    SimpleDateFormat sdfrmt = new SimpleDateFormat("YYYY-mm-dd");
		    sdfrmt.setLenient(false);

		    try {
		        @SuppressWarnings("unused")
				Date javaDate = sdfrmt.parse(strDate); 
		    } catch (ParseException e){
		        return false;
		    }
		    return true;
		}
	}

}
