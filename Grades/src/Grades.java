import java.util.Scanner;
 
public class Grades {
    public static void main(String[] args){
        System.out.println("Enter Grade: ");
        Scanner myGrade = new Scanner(System.in);
        
        float Grade = myGrade();
        System.out.println("Grade is: " + Grade);
        
        int grade = 0;
        int count1=0;
        do {
        	System.out.println(grade);
			count1++;
			if (grade<0) {
				break;
			}
        }
		while (grade >= 93 && grade<= 100); {
        	System.out.println("A");
        	
        } if (grade >= 90 && grade<= 93) {
        	System.out.println("A-");
        } else if (grade >= 87 && grade<= 90) {
        	System.out.println("B+");
        }else if (grade >= 83 && grade<= 87) {
        	System.out.println("B");
        }else if (grade >= 80 && grade<= 83) {
    	System.out.println("B-");
        }else if (grade >= 77 && grade<= 80) {
        	System.out.println("C+");
        }else if (grade >= 73 && grade<= 77) {
        	System.out.println("C");
        }else if (grade >= 70 && grade<= 73) {
        	System.out.println("C-");
        }else if (grade >= 60 && grade<= 70) {
        	System.out.println("D");
        }else if(grade >= 0 && grade<= 60) {
        	System.out.println("F");
        }else {
        	System.out.println("Not available");
        }
        System.out.println("Total Number of Grades:" + grade); 
        System.out.println("Number of A's: " + count1);
        System.out.println("Number of A-'s: " + count1);
        System.out.println("Number of B+'s: " + count1);
        System.out.println("Number of B's: "+ count1);
        System.out.println("Number of B-'s: "+ count1);
        System.out.println("Number of C+'s: "+ count1);
        System.out.println("Number of C's: "+ count1);
        System.out.println("Number of C-'s: "+ count1);
        System.out.println("Number of D's: "+ count1);
        System.out.println("Number of F's: "+ count1);
}

	private static float myGrade() {
		// TODO Auto-generated method stub
		return 0;
	}

	}
