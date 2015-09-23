import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class SignInFile {
	
	private String path;
	private ArrayList<Student> students;

	public SignInFile() {
		Calendar cal = new GregorianCalendar();
		path = String.format("%s-%s meeting.txt", cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH));
		students = new ArrayList<Student>();
		loadFile();
		
	}
	
	private void loadFile(){
		try (BufferedReader br = new BufferedReader(new FileReader(path)))
		{

			String sCurrentLine;
			String[] aCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				aCurrentLine = sCurrentLine.split(",");
				students.add(new Student(aCurrentLine[0], aCurrentLine[1], aCurrentLine[2], Integer.parseInt(aCurrentLine[3]), aCurrentLine[4]));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)))) {
				out.println(students.get(students.size()-1).save());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addStudent(Student student){
		students.add(student);
	}
	
	public ArrayList<Student> getStudents(){
		return students;
	}
	
	public boolean signedIn(Student student){
		boolean signedIn = false;
		for(int i=0; i<students.size(); i++){
			if(student.toString().equals(students.get(i).toString())){
				signedIn = true;
			}
		}
		return signedIn;
		
	}

}
