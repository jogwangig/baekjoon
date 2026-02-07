package problem_9466;

import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class IsolationStudentsCalculator {
	
	private int isolatedStudentsNum = 0;
		
	private Students students;
		
	public IsolationStudentsCalculator(Students students) {
		this.students = students;
	}
	
	public int run() {
		goRoundStudents();
		return isolatedStudentsNum;
	}
	
	private void goRoundStudents() {
		for(int i = 1; i <= students.size(); i++) {
			if(students.isNullStudent(i)) continue;
			
			Student s = students.getStudentByNum(i);
			
			Optional<Set<Student>> team = TeamDetector.getTeamOfStudent(s, students);
						
			team.ifPresentOrElse((t)->{
				students.excludeTeam(t);
			},
				()->{
					students.exclude(s);
					isolatedStudentsNum++;});
			
		}
	}

}
	
	