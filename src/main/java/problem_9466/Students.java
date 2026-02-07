package problem_9466;

import java.util.Set;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Students {
	
	private Student[] students;
	
	public int size() {
		return students.length - 1;
	}
	
	public void excludeTeam(Set<Student> team) {
		for(Student s : team) 
			exclude(s);
	}
	
	public void exclude(Student s) {
		students[s.getStudentNum()] = Student.getNullStudent();
	}
	
	public boolean isNullStudent(int studentNum) {
		return students[studentNum].isNullStudent();
	}
	
	public Student getTargetStudent(Student s) {
		return students[s.getTargetStudentNum()];
	}
	
	public Student getStudentByNum(int studentNum) {
		return students[studentNum];
	}
}
