package university;

public class Student {
	/**This class was made to create a student object to hold a students name,
	 * how many classes that student has. It is used to return those fields in a 
	 * proper manner. 
	 */

	private String name;
	private int numCourses;
	//Good because in this case I technically only need a student to hold a string
	//but if I wanted to add ID's to students it would be easier this way

	public Student(Student other){
		other.name= name;
		other.numCourses = numCourses;
	}

	public Student(String studentName){
		name = studentName;
		numCourses=0;
	}

	public void addToCourse(){
		numCourses++;
	}

	public void removeFromCourse(){
		numCourses--;
	}

	public String getName(){
		return name;
	}

	public int getNumCourses(){
		return numCourses;
	}
}
