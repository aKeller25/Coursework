package university;
import java.util.ArrayList;
public class University {
	
	/**This class was made to hold an arrayList of courses and work as a 
	 * university should. This class can be used to add/cancel a course,
	 * return the number of courses, add/remove a student from a course,
	 * return the number of students in a course, check if a student is 
	 * registered for a course, and check how many students are registered
	 */
	
	private ArrayList<Course> courseList = new ArrayList<Course>();
	//ArrayList<Course> (arraylist of type Coruse instead of type object)
	//XXX = BUG
	//TODO = TO DO LATER
	//I could have added an arrayList of students instead of having the students
	//stored in each course

	
	//If courseExists returns false (the class doesn't exist) it makes the 
	//statement true and if it's true then it adds the course and increments the
	//number of coruses the university has
	public University addCourse(String department, int number, int numSeats) {
		if (!courseExists(department,number)){
			courseList.add(new Course(department, number, numSeats));
		}				
		return this;
	}

	//If coruseExists returns true (the class does exist), then it finds the 
	//Course, removes all students from the course, and remove the course from
	//the courseList
	public boolean cancelCourse(String department, int number) {
		if (courseExists(department,number)){
			Course canceled = findCourse(courseList, department, number);
			canceled.removeAllStudents();
			courseList.remove(canceled);
			return true;
		}
		else
			return false;
	}				

	//Returns the number of courses in the university
	public int numCourses() {
		return courseList.size();
	}

	/** [Would appear in a javaDoc]
	 * Adds a student with name(name)to a course if the course exists, the 
	 * student isn't in the course, the student hasn't already registered for
	 * 5 courses, and the 	class isn't already full. If a student is successfully 
	 * added, it returns true, otherwise it returns false
	 * @param department- name of the course's department
	 * @param number- course's number
	 * @param name- student's name
	 * @return true if the student is added and false if it isn't
	 */
	public boolean add(String department, int number, String name) {
		boolean added = false;
		Student student=null;
		//Checks if the course exists and if it does it finds the course
		if (courseExists(department, number)){
			Course course = findCourse(courseList, department, number);
			//Checks if the student isn't already in the course and the course
			//isn't full
			if (!(course.studentExistsInCourse(name)) && 
					course.getCourseSeats()> course.getNumStudents()){
				/*It goes through all of the courses, creates a 
				 * temporary variable to find the student with name(name), 
				 * checks if it's not null and if it isn't assign temp to student.
				 * 
				 * This was done to avoid the fact that, if the student was in
				 * previous courses but not in the last course, the variable
				 * student would not be over-written with the value null*/
				for (Course c: courseList){
					Student temp = c.findStudent(name);
					if (temp != null){
						student=temp;
					}
				}
				/*Checks if a student doesn't already exist and if it doesn't
				then it creates the student with name(name) and adds them to 
				the course. Although if it does exist and the student hasn't
				registered for the maximum of 5 courses, it adds the student
				to the course*/
				if (student==null){ 
					course.addStudent(new Student(name));
					added= true;
				}

				else if (student.getNumCourses()<5){
					course.addStudent(student);
					added= true;
				}
			}
		}
		return added;
	}

	//If the course does exist, then it finds the course and returns the number
	//of students in that course, otherwise return -1;
	public int numStudentsInCourse(String department, int number) {
		if (courseExists(department, number)){
			return findCourse(courseList, department, number).getNumStudents();
		}
		else return -1;
	}


	//If the course exists, then find the course, if not return false. 
	//If that student exists in that course and the course exists, return true, 
	//else return false.
	public boolean isRegisteredForCourse(String department, int number,
			String name) {
		boolean registered=false;
		if (courseExists(department, number)){
			Course course = findCourse(courseList, department, number);
			if (course.studentExistsInCourse(name)){
				registered= true;
			}
		}
		return registered;
	}

	/*Going through all of the courses in courseList, it checks if the student
	with the name(name) exists, and if it does exist then numRegistered 
	(the number of courses the student is registered for) is set to the number
	of courses that that student is registered for. If the student doesn't 
	exist (not in the university), numRegistered remains 0. No matter what 
	numRegistered is returned*/
	public int numCoursesRegisteredFor(String name) {
		int numRegistered=0;
		for (Course c: courseList){
			if (c.studentExistsInCourse(name)){
				numRegistered= c.findStudent(name).getNumCourses();
			}
		}
		return numRegistered;
	}

	//Removes a student from a course if the course exists and the student is in
	//that course. If the student is successfully removed, then t returns true
	public boolean drop(String department, int number, String name) {
		boolean dropped = false;
		//Check if a course exists and if it does, find that course
		if (courseExists(department, number)){
			Course course = findCourse(courseList, department, number);
			//After the above is executed, it checks if the student is in that
			//course, and if it is then it find the student and removes the student
			if (course.studentExistsInCourse(name)){
				Student student = course.findStudent(name);
				course.removeStudent(student);
				dropped= true;
			}
		}
		return dropped;
	}

	/*Going through all of the courses, if the student with name(name) exists
	in that class, the student is found and removed and registrationCanceled
	is set to true. If the student doesn't exist then registrationCanceled
	is false. No matter what registrationCanceled is false*/
	public boolean cancelRegistration(String name) {
		boolean registrationCanceled=false;
		for (Course c: courseList){
			Student student = c.findStudent(name);
			if (student!=null){
				c.removeStudent(student);
				registrationCanceled= true;
			}
		}
		return registrationCanceled;
	}

	//A helper method that checks if a coruse exists (true) 
	//or doesn't exist (false)
	private boolean courseExists(String department, int number){
		for (Course c : courseList){
			if ((c.getDepartment().equals(department)) && 
					(c.getCourseNumber()==number)){
				return true;
			}
		}
		return false;
	}

	//Finds the course and returns it as a reference. Otherwise
	//it returns a null
	private Course findCourse(ArrayList<Course> courseList, String department, 
			int number){
		Course foundCourse=null;
		for (Course c : courseList){
			if ((c.getDepartment().equals(department)) && 
					(c.getCourseNumber()==number)){
				foundCourse= c;
			}
		}
		return foundCourse;
	}
}
