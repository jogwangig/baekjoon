package problem_9466;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IsolationStudentsCalculator {
	
	private int isolatedStudentsNum = 0;
	
	private int teamsNum = 0;
	
	private Student[] students;
	
	public IsolationStudentsCalculator(Student[] students) {
		this.students = students;
	}
	
	public int run() {
		goRoundStudents();
		return isolatedStudentsNum;
	}
	
	private void goRoundStudents() {
		for(Student s : students) {
			
			if(s == null)
				return;
						
			if(isContainedInTeam(s) ){
				excludeStudentsOfTeam(
						getStudentsOfTeam(s)
				);
				
				teamsNum++;
			}else {
				excludeStudent(s);
				isolatedStudentsNum++;
				
			}
		}
	}
	
	private void excludeStudentsOfTeam(Set<Student> studentsOfTeam) {
		for(Student s : studentsOfTeam)
			excludeStudent(s);	
	}
	
	private Set<Student> getStudentsOfTeam(final Student student){
		Student currentSelectedStudent = nextStudent(student);
				
		Set<Student> visitedStudents = new HashSet<>();
		
		visitedStudents.add(student);
		
		while(currentSelectedStudent.equals(student)) {
			visitedStudents.add(currentSelectedStudent);
		}
		
		return visitedStudents;
	}
	
	
	
	
	
	private boolean isContainedInTeam(final Student student) {
		
		Student targetStudent = nextStudent(student);
		
		Set<Student> visitedStudents = new HashSet<>();
		
		while(isEmpty(targetStudent) && 
				!visitedStudents.contains(targetStudent)) {
				
			if (targetStudent.equals(student))	return true;
			
			visitedStudents.add(targetStudent);
			
			targetStudent = nextStudent(student);
		}
		
		return false;
	}
	
	
	private Student getStudent(int index) {
		return students[index];
	}
	
	private boolean isEmpty(Student student) {
		int studentNum = student.getStudentNum();
		return this.students[studentNum] == null;
	}
	
	private Student nextStudent(Student student) {
		int nextStudentNum = student.getTargetStudentNum();
		return students[nextStudentNum];
	}
	
	private void excludeStudent(Student student) {
		students[student.getStudentNum()] = null;
	}

}
