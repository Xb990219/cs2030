import java.util.Scanner;

public class Main {
    /** This is the main.
     * 
     * @param args args.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numOfCommands = sc.nextInt();
        Roster roster = new Roster("TestSet");
        for (int i = 0; i < numOfCommands; i++) {
            String a = sc.next(); //name
            String b = sc.next(); //course
            String c = sc.next(); // assignment
            String d = sc.next(); // grade
            roster = roster.add(a, b, c, d);
        }
        //System.out.print(roster);
        while (sc.hasNext()) {
            String student = sc.next();
            String course = sc.next();
            String assessment = sc.next();
            String grade = roster.getGrade(student, course, assessment);
            System.out.println(grade);
        }
        sc.close();
    }
}