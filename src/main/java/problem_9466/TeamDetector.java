package problem_9466;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamDetector {
	
	public static Optional<Set<Student>> getTeamOfStudent(Student s, Students students) {
		if(s.isNullStudent()) throw new RuntimeException("학생은 무조건 존재해야 합니다");
		
		Student targetStudent = students.getTargetStudent(s);
		
		Set<Student> visitedStudents = new HashSet<>();
		
		while(!targetStudent.isNullStudent() && !visitedStudents.contains(targetStudent)) {
			
			if (targetStudent.equals(s)) {
				visitedStudents.add(s);
				return Optional.of(visitedStudents);
			}
			
			visitedStudents.add(targetStudent);
			
			targetStudent = students.getTargetStudent(targetStudent);
		}
		
		if(visitedStudents.contains(targetStudent)) {
			visitedStudents = new HashSet<>();
			do {
				visitedStudents.add(targetStudent);
				
				targetStudent = students.getTargetStudent(targetStudent);
			}while(visitedStudents.contains(targetStudent));
			
			return Optional.of(visitedStudents);
		}
		
		return Optional.empty();
	}
	
}
