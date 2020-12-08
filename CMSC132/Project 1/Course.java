package university;

import java.util.ArrayList;

public class Course {
	/**This class was made to hold all the elements that make a course has such 
	 * as it's department, number, number of seats, how many students are in it, 
	 * as well as a list of students in an arrayList. This is used to 
	 * add/remove students, find students in it, find if a student is enrolled in
	 * it, and to return it's elements in a proper way.
	 */
	
	private String courseDeparment;
	private int courseNumber;
	private int courseSeats;
	private int numStudents;
	private ArrayList<Student> studentList = new ArrayList<Student>();

	public Course(String department, int number, int numSeats) {
		courseDeparment = department;
		courseNumber = number;
		courseSeats = numSeats;
		numStudents=0;
	}

	//Adds a student to the course if the number of seats in the course is greater
	//than the number of students in the course (the course isn't full) and if
	//the student isn't already in the course
	public void addStudent(Student studentToAdd){
			studentToAdd.addToCourse();
			studentList.add(studentToAdd);
			numStudents++;
	}

	//Removes a student from the course if the student is already in that course
	public void removeStudent(Student studentToRemove){
			studentToRemove.removeFromCourse();
			studentList.remove(studentToRemove);
			numStudents--;
	}

	//Removes all students in the course by going the studentList and removing
	//then one by one
	public void removeAllStudents(){
		for (Student s : studentList){
			s.removeFromCourse();
			numStudents--;
		}
	}

	public String getDepartment(){
		return courseDeparment;
	}

	public int getCourseNumber(){
		return courseNumber;
	}

	public int getCourseSeats(){
		return courseSeats;
	}

	public int getNumStudents(){
		return numStudents;
	}

	//A method that creates a copy of the studentList array and each student in it
	public ArrayList<Student> getStudents(){
		ArrayList<Student> copy = new ArrayList<Student>();
		for (Student s : studentList){
			copy.add(new Student(s));
		}
		return copy;
	}

	//A helper method that is used outside of the class to find a student in a 
	//course
	public Student findStudent(String studentName){
		Student studentToReturn=null; 
		for (Student s : studentList){
			if (s.getName().equals(studentName)){
				studentToReturn= s;
			}
		}
		return studentToReturn;
	}

	//A helper method that is used outside of the class to find if a student is 
	//in a course
	public boolean studentExistsInCourse(String name){
		for (Student s : studentList){
			if (s.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
}
