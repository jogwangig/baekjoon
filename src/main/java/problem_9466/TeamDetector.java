package problem_9466;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import problem_9466.Main.Student;
import problem_9466.Main.Students;

@AllArgsConstructor
public class TeamDetector {
	
	public static int getTeamOfStudent(Student s, Students students) {
		if(s.isNullStudent()) throw new RuntimeException("학생은 무조건 존재해야 합니다");
		
		int count = 0;
		
		Student targetStudent = students.getTargetStudent(s);
		
		Set<Student> visitedStudents = new HashSet<>();
		
		visitedStudents.add(s);
		
		while(!targetStudent.isNullStudent()) {
			
			if (visitedStudents.contains(targetStudent)) {//cycle
				
				Student temp = students.getTargetStudent(targetStudent);
				count++;
				
				while(!temp.equals(targetStudent)) {
					count++;
					temp = students.getTargetStudent(temp);
				}
				
				students.excludeTeam(visitedStudents);
				return count;
			}
			
			visitedStudents.add(targetStudent);
			
			targetStudent = students.getTargetStudent(targetStudent);
		}
		
		students.excludeTeam(visitedStudents);
		
		return count;
		
	}
	
}
