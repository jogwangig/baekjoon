package problem_9466;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

}
