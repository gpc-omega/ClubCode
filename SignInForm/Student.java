import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student {
	
	private String name;
	private String phone;
	private String email;
	private int studNum;
	private String time;
	

	public Student(String name, String phone, String email, int studNum) throws IllegalArgumentException{
		Calendar calendar = new GregorianCalendar();
		if(name.length() < 1){
			throw new IllegalArgumentException("Name field left blank. You must enter your name!");
		}
		if(email.length() > 0 && email.length() < 7){
			throw new IllegalArgumentException("Invalid email. Please enter a proper email address");
		}
		if(phone.length() > 0 && (phone.length() < 10 || phone.length() > 15)){
			throw new IllegalArgumentException("Phone number is invalid. Please enter a valid 10 digit number");
		}
		if(studNum > 901000000 || studNum < 900000000){
			throw new IllegalArgumentException("Student number is invalid. You must enter a valid 900#");
		}
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.studNum = studNum;
			
		this.time = String.format("%d:%d",calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}
	
	public Student(String name, String phone, String email, int studNum, String time) throws IllegalArgumentException{
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.studNum = studNum;
		this.time = time;
	}
	
	public String save(){
		return String.format("%s,%s,%s,%d,%s", name, phone, email, studNum, time);
	}

	public String toString(){
		return String.format("%s,%s,%s,%d", name, phone, email, studNum);
	}
}
