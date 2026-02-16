package problem_9466;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int testNum = scanner.nextInt();
		
		List<Integer> results = new ArrayList<>();
		
		
		for(int j = 0; j < testNum; j++) {
			
			int studentsNum = scanner.nextInt();
		
			Student[] students = new Student[studentsNum + 1];
			
			students[0] = Student.getNullStudent();
			
			for(int i = 1; i <= studentsNum; i++) {
				students[i] = new Student(i, scanner.nextInt());
			}
			
			IsolationStudentsCalculator isc = new IsolationStudentsCalculator(
						new Students(students)
					);
			
			results.add(isc.run());
			
			
		}
		
		results.forEach(System.out::println);
		scanner.close();

	}
	
	
	
	static class Student {
		
		int studentNum;
		
		int targetStudentNum;
		
		private static Student nullStudent = new Student(Integer.MIN_VALUE, Integer.MIN_VALUE);
		
		public static Student getNullStudent() {
			return nullStudent;
		}
		
		public Student(int studentNum, int targetStudentNum ) {
			this.studentNum = studentNum;
			this.targetStudentNum = targetStudentNum;
		}
		
		public int getStudentNum() {
			return studentNum;
		}
		
		public int getTargetStudentNum() {
			return targetStudentNum;
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
	
	
	static class Students {
		
		private Student[] students;
		
		public Students(Student[] students) {
			this.students = students;
		}
		
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
	
	static class TeamDetector {
		
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
				}while(!visitedStudents.contains(targetStudent));
				
				return Optional.of(visitedStudents);
			}
			
			
			return Optional.empty();
		}
		
	}
	
	static class IsolationStudentsCalculator {
		
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
					if(t.contains(s)) {
						students.excludeTeam(t);
					}else {
						students.exclude(s);
						isolatedStudentsNum++;
						students.excludeTeam(t);
					}	
				},
					()->{
						students.exclude(s);
						isolatedStudentsNum++;});
				
			}
		}

	}
		
		
	

}
