package dto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserStore {
	
	private ArrayList<String> users;
	
	public UserStore() {
		
	}
	
	public boolean checkCpr(String cpr){
		Date date = null;
		//First try and catch for "-" error 
		try{
		String[] parts = cpr.split("-");
		String dateNumber = parts[0]; //
		String number = parts[1]; //
		
		if(dateNumber.length()==6 && number.length()==4){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
				date = sdf.parse(dateNumber);
				if (!dateNumber.equals(sdf.format(date))) {
					return false;
				}
				else{
					return true;
				}
			} catch (ParseException ex) {
				return false;
			}	
			
		}
		else 
			return false;
		
		}catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	

}
