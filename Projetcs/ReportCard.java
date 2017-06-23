import java.awt.List;
//import java.util.List;
import java.util.ArrayList;

public class ReportCard {
	



	    private static final String SCHOOL_NAME = "I.M. Panagiotopoulos";

	    private String studentFirstName;

	    private String studentLastName;

	    private String teachersLastName;

	    private int gradeLevel;

	    private String[] courseNames;

	    private int[] courseGrades;

	    private String[] courseLetterGrades;



	    // Default constructor

	    public ReportCard() {

	        studentFirstName = "John";

	        studentLastName = "Pappas";

		teachersLastName = " Papadopoulos";

	        gradeLevel = 3;

	        ArrayList<String[]> courses =  new ArrayList <String[]>(); List.add(new String[]{"Math", "Chemistry", "Physics", "Greek",});

	        ArrayList <int []> Grades = new ArrayList<int[]>(); List.add(new int[] {15, 12,17, 10});

	        ArrayList <String[]>letGrades = new Arraylist <String[]>(); list.add(new String[] {getletGrades(Grades[0]), getgetletGrades(Grades[1]),

	                getletGrades(Grades[2]), getletGrades(Grades[3])});

	    }



	    // Custom constructor

	    public ReportCard(String firstName, String lastName,String teacher, int classRoom, String c1, String c2, String c3, String c4, int c1g, int c2g, int c3g, int c4g) {

	        studentFirstName = firstName;

	        studentLastName = lastName;

		teachersLastName= teacher;

	        gradeLevel = classRoom;

		   ArrayList<String[]> courses = new ArrayList <String[]>(); list.add(new String[]{"c1", "c2", "c3", "c4",....});

	        ArrayList <int []> Grades = new Arraylist<int[]>(); list.add(new int[] {c1g, c2g,c3g,c4g});

	        ArrayList <String[]>letGrades = new Arraylist <String[]>(); list.add(new String[] {getletGrades(Grades[0]), getgetletGrades(Grades[1]),

	                getletGrades(Grades[2]), getletGrades(Grades[3])});

	   }



	    @Override

	    public String toString() {



	        return "School Name: " + SCHOOL_NAME + "\n" +

	                "Student Name: " + getStudentFirstName() + " " + getStudentLastName() + "\n" +

			"Teacher:" + getTeacher()+"\n "+ 

	                "Grade Level: " + getGradeLevel() + "\n" +

	                "Course One: " + courses[0] + " taksh: " + Grades[0] + " letter grade: " + getletGrades(Grades[0]) + "\n" +

	                "Course Two: " + courses[1] + " taksh: " + Grades[1] + " letter grade: " + getletGrades(Grades[1]) + "\n" +

	                "Course One: " + courses[2] + " taksh: " + Grades[2] + " letter grade: " + getletGrades(Grades[2]) + "\n" +

	                "Course One: " + courses[3] + " taksh: " + Grades[3] + " letter grade: " + getletGrades(Grades[3]);

	    }



	    public void setStudentFirstName(String firstname) {

	        studentFirstName = firstname;

	    }



	    public void setStudentLastName(String lastname) {

	        studentLastName = lastname;

	    }



	    public void setTeacher(String teacher) {

	        teacherLastName = teacher;

	    }



	    public void setGradeLevel(int classRoom) {

	        gradeLevel = taksh;

	    }



	    private String getStudentFirstName() {

	        return studentFirstName;

	    }





	    private String getTeacher() {

		return teacher;

	    }



	    private String getStudentLastName() {

	        return studentLastName;

	    }



	    private int getGradeLevel() {

	        return gradeLevel;

	    }



	    private String getletGrades(int grade) {

	        if (grade >= 19) {

	            return "A";

	        } else if (grade >=16 ) {

	            return "B"; 

	        } else if (grade >= 14 ) {

	            return "C";

	        } else if (grade >= 12) {

	            return "D";

	        } else {

	            return "F";

	        }

	    }

	
}
