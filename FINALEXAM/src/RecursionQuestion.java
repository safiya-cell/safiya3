
import java.util.Scanner;

public class RecursionQuestion {
	public static int binarySearch(Car[] cars, Car c) {
	    System.out.println("Looking for Car: " + c.toString());
	    return findCar(cars, c, 0, cars.length - 1);
	}
    
    private static int findCar(Car[] cars, Car c, int s, int e) {
    	if (s > e) {
            System.out.println("s=" + s + ", e=" + e);
            System.out.println("Not Found\n");
            return -1;
        }

    	 int mid = (s + e) / 2;
         System.out.println("s=" + s + ", e=" + e + ", mid=" + mid);
         int comparison = cars[mid].compareTo(c);

         if (comparison == 0) {
             System.out.println("FOUND at " + mid + "\n");
             System.out.println();
             return mid;
         } else if (comparison > 0) {
             System.out.println("go left");
             return findCar(cars, c, s, mid - 1);
         } else {
             System.out.println("go right");
             return findCar(cars, c, mid + 1, e);
         }
     }

    

    // Do not remove main method or its content
    public static void main(String[] args) {
        Car[] cars = new Car[10];
        int[][] seatConfigurations = {
            new int[]{2, 2}, new int[]{2, 3},
            new int[]{2, 2, 3}, new int[]{2, 3, 3}, new int[]{2, 3, 4}, new int[]{2, 4, 3},
            new int[]{2, 3, 5}, new int[]{2, 4, 4}, new int[]{2, 4, 5}, new int[]{3, 4, 5}
        };
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(2, 4, seatConfigurations[i]);
        }
        Scanner kb = new Scanner(System.in); // Scanner for reading input
        // System.out.print("enter seat info");   // Uncomment only when testing on your local system
        String[] input = kb.nextLine().split(" ");
        int[] rowSeats = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            rowSeats[i] = Integer.parseInt(input[i]);
        }
        Car target = new Car(2, 4, rowSeats);
        int result = binarySearch(cars, target);

    }
}