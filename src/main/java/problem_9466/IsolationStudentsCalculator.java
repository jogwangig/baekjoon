package problem_9466;

import java.util.ArrayList;
import java.util.List;

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
			
			int currentStudentNum  = s.getSelectedStudentNum();
			
			if(isContainedInTeam(currentStudentNum) ){
				excludeStudentsOfTeam(
						getStudentsOfTeam(currentStudentNum));
				
				teamsNum++;
			}else {
				students[s.getSelectedStudentNum()] = null;
				isolatedStudentsNum++;
				
			}
		}
	}
	
	private void excludeStudentsOfTeam(List<Integer> studentsOfTeam) {
		for(int i : studentsOfTeam)
			students[i] = null;
	}
	
	private List<Integer> getStudentsOfTeam(final int studentNum){
		int  currentSelectedStudentNum = getStudent(studentNum).getSelectedStudentNum();
		
		Student currentSelectedStudent = getStudent(currentSelectedStudentNum);
		
		List<Integer> visitedStudents = new ArrayList<Integer>();
		
		visitedStudents.add(studentNum);
		
		while(currentSelectedStudentNum == studentNum) {
			visitedStudents.add(currentSelectedStudentNum);
		}
		
		return visitedStudents;
	}
	
	
	
	
	
	private boolean isContainedInTeam(final int studentNum) {
		
		int  currentSelectedStudentNum = getStudent(studentNum).getSelectedStudentNum();
		
		Student currentSelectedStudent = getStudent(currentSelectedStudentNum);
		
		List<Integer> visitedStudents = new ArrayList<Integer>();
		
		while(currentSelectedStudent != null && 
				!visitedStudents.contains(currentSelectedStudent.getSelectedStudentNum())) {
				
			if (currentSelectedStudent.getSelectedStudentNum() == studentNum)
					return true;
			
			visitedStudents.add(currentSelectedStudent.getSelectedStudentNum());
		}
		
		return false;
	}
	
	
	private Student getStudent(int index) {
		return students[index];
	}

}
