package problem_9466;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
	
	int studentNum;
	
	int targetStudentNum;
	
	@Override
	public int hashCode() {
		return Objects.hash(studentNum, targetStudentNum);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		
		if(o == null || getClass() != o.getClass()) return false;
		
		Student s = (Student)o;
		
		return (studentNum == s.studentNum) &&
				(targetStudentNum == s.targetStudentNum);
	}

}
