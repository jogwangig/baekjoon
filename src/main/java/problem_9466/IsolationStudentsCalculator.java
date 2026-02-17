package problem_9466;

import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import problem_9466.Main.Student;
import problem_9466.Main.Students;
import problem_9466.Main.TeamDetector;


@AllArgsConstructor
public class IsolationStudentsCalculator {
	
	private int isolatedStudentsNum = 0;
		
	private Students students;
		
	public IsolationStudentsCalculator(Students students) {
		this.students = students;
		isolatedStudentsNum = students.size();
	}
	
	public int run() {
		goRoundStudents();
		return isolatedStudentsNum;
	}
	
	private void goRoundStudents() {
		for(int i = 1; i <= students.size(); i++) {
			if(students.isNullStudent(i)) continue;
			
			Student s = students.getStudentByNum(i);
			
			isolatedStudentsNum = isolatedStudentsNum - TeamDetector.getTeamOfStudent(s, students);
			
		}
	}

}
	
	