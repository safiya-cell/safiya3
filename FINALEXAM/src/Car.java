


public class Car extends Vehicle implements Comparable<Car>, Announcements {
    private int numDoors;
    private int numWindows;
    private Person[][] peopleOnBoard;

    // Constructors
    public Car(int numDoors, int numWindows) {
        super(2, 2);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, int numSeatsPerRow) {
        super(2, numSeatsPerRow);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, int[] numSeatsPerRow) {
        super(numSeatsPerRow);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }

    public Car(int numDoors, int numWindows, Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        super(driver, numSeatsPerRow);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }
    
    public Person[][] getPeopleOnBoard() {
        return peopleOnBoard;
    }
    
//    public boolean canOpenDoor(Person p) {
//        return false; 
//    }

    public boolean canOpenWindow(Person p) {
        return p.getAge() >= 3; // Example condition
    }

    // Method to check if a person can open a door
    public boolean canOpenDoor(Person p) {
        int[] location = getLocationOfPersonInVehicle(p);
        if (location[0] != -1) {
            return false;
        }
        int row = location[0];
        int col = location[1];
        int doorsPerRow = numDoors / numberOfRows;
        return (col == 0 || col == numSeatsPerRow[row] - 1) && row < doorsPerRow && p.getAge() > 5;
    }

//    // Method to check if a person can open a window
//    public boolean canOpenWindow(Person p) {
//        int[] location = getLocationOfPersonInVehicle(p);
//        if (location[0] == -1) {
//            return false;
//        }
//        int row = location[0];
//        int col = location[1];
//        int windowsPerRow = numWindows / numberOfRows;
//        return (col == 0 || col == numSeatsPerRow[row] - 1) && row < windowsPerRow && p.getAge() > 2;
//    }

    // Getter for numDoors
    public int getNumDoors() {
        return numDoors;
    }

    // Getter for numWindows
    public int getNumWindows() {
        return numWindows;
    }

    // Properly overridden equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return numDoors == car.numDoors &&
               numWindows == car.numWindows &&
               numberOfRows == car.numberOfRows &&
               maxSeatsPerRow == car.maxSeatsPerRow &&
               java.util.Arrays.equals(numSeatsPerRow, car.numSeatsPerRow);
    }

    // Properly overridden toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("number of doors = ");
        sb.append(String.format("%02d", numDoors)).append(" | number of windows = ");
        sb.append(String.format("%02d", numWindows)).append(" | number of rows = ");
        sb.append(String.format("%02d", numberOfRows)).append(" | seats per row = [");
        for (int i = 0; i < numSeatsPerRow.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(numSeatsPerRow[i]);
        }
        sb.append("] | names of people on board = [");
        boolean first = true;
        for (Person[] row : personsOnBoard) {
            for (Person p : row) {
                if (p != null) {
                    if (!first) sb.append(",");
                    sb.append(p.getName());
                    first = false;
                }
            }
        }
        sb.append("]\n");
        return sb.toString();
    }

    // Properly overridden compareTo method
    @Override
    public int compareTo(Car c) {
        int thisSeats = java.util.Arrays.stream(numSeatsPerRow).sum();
        int otherSeats = java.util.Arrays.stream(c.numSeatsPerRow).sum();
        return Integer.compare(thisSeats, otherSeats);
    }

    // Method to load a passenger
    @Override
    public boolean loadPassenger(Person p) {
        if (p.getAge() < 5 || p.getHeight() < 36) {
            for (int i = 1; i < numberOfRows; i++) {
                for (int j = 0; j < numSeatsPerRow[i]; j++) {
                    if (personsOnBoard[i][j] == null) {
                        personsOnBoard[i][j] = p;
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i < numberOfRows; i++) {
                for (int j = 0; j < numSeatsPerRow[i]; j++) {
                    if (personsOnBoard[i][j] == null) {
                        personsOnBoard[i][j] = p;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Method to load multiple passengers
    @Override
    public int loadPassengers(Person[] peeps) {
        int count = 0;
        for (Person p : peeps) {
            if (loadPassenger(p)) {
                count++;
            }
        }
        return count;
    }

    
    @Override
    public String departure() {
        return "All Aboard\n";
    }

    @Override
    public String arrival() {
        return "Everyone Out\n";
    }

    @Override
    public String doNotDisturbTheDriver() {
        return "No Backseat Driving\n";
    }
} 