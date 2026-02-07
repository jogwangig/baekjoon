package problem_9466;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Student {
	
	int studentNum;
	
	int targetStudentNum;
	
	private static Student nullStudent = new Student(Integer.MIN_VALUE, Integer.MIN_VALUE);
	
	public static Student getNullStudent() {
		return nullStudent;
	}
	
	public boolean isNullStudent() {
		return (studentNum == Integer.MIN_VALUE) && 
				(targetStudentNum == Integer.MIN_VALUE);
	}
	
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
	
	@Override
	public String toString() {
		return "학생 번호 : " + studentNum + " 지명한 학생번호 : " + targetStudentNum;
	}

}
