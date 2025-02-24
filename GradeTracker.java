import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        int[] scores = new int[numStudents];
        char[] letterGrades = new char[numStudents];
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter numeric grade for student " + (i + 1) + ": ");
            scores[i] = scanner.nextInt();
            letterGrades[i] = getLetterGrade(scores[i]); 
        }
        int sum = 0;
        int highest = scores[0];
        int lowest = scores[0];

        for (int score : scores) {
            sum += score;
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }
        double average = (double) sum / numStudents;
        System.out.println("\nResults:");
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Student " + (i + 1) + ": Numeric Grade = " + scores[i] + ", Letter Grade = " + letterGrades[i]);
        }
        System.out.println("\nClass Statistics:");
        System.out.println("Average score: " + average);
        System.out.println("Highest score: " + highest);
        System.out.println("Lowest score: " + lowest);

        scanner.close();
    }
    public static char getLetterGrade(int score) {
        if (score >= 90) {
            return 'A';
        } else if (score >= 80) {
            return 'B';
        } else if (score >= 70) {
            return 'C';
        } else if (score >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}