
public abstract class Vehicle {
    protected Person[][] personsOnBoard;
    protected int numberOfRows;
    protected int maxSeatsPerRow;
    protected int[] numSeatsPerRow;
    private Person driver;

    // Constructor for uniform seat rows
    public Vehicle(int numRows, int numSeatsPerRow) {
        this.numberOfRows = numRows;
        this.numSeatsPerRow = new int[numRows];
        this.maxSeatsPerRow = numSeatsPerRow;
        for (int i = 0; i < numRows; i++) {
            this.numSeatsPerRow[i] = numSeatsPerRow;
        }
        this.personsOnBoard = new Person[numRows][numSeatsPerRow];
    }

    // Constructor for non-uniform seat rows
    public Vehicle(int[] numSeatsPerRow) {
        this.numberOfRows = numSeatsPerRow.length;
        this.numSeatsPerRow = numSeatsPerRow;
        this.maxSeatsPerRow = 0;
        for (int seats : numSeatsPerRow) {
            if (seats > maxSeatsPerRow) {
                maxSeatsPerRow = seats;
            }
        }
        this.personsOnBoard = new Person[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            this.personsOnBoard[i] = new Person[numSeatsPerRow[i]];
        }
    }

    // Constructor with driver
    public Vehicle(Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        this(numSeatsPerRow);
        setDriver(driver);
    }

    // Abstract methods
    public abstract boolean loadPassenger(Person p);
    public abstract int loadPassengers(Person[] peeps);

    // Set driver
    public void setDriver(Person p) throws InvalidDriverException {
        if (!p.hasDriverLicense()) {
            throw new InvalidDriverException("Driver must have a driver's license");
        }
        this.driver = p;
        this.personsOnBoard[0][0] = p;
    }

    // Getters
    public Person getDriver() {
        return driver;
    }

    public boolean hasDriver() {
        return driver != null;
    }

    public int getNumberOfAvailableSeats() {
        int count = 0;
        for (Person[] row : personsOnBoard) {
            for (Person seat : row) {
                if (seat == null) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getNumberOfAvailableSeatsInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return -1;
        }
        int count = 0;
        for (Person seat : personsOnBoard[row]) {
            if (seat == null) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfPeopleOnBoard() {
        int count = 0;
        for (Person[] row : personsOnBoard) {
            for (Person seat : row) {
                if (seat != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getNumberOfPeopleInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return -1;
        }
        int count = 0;
        for (Person seat : personsOnBoard[row]) {
            if (seat != null) {
                count++;
            }
        }
        return count;
    }

    public Person getPersonInSeat(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numSeatsPerRow[row]) {
            return null;
        }
        return personsOnBoard[row][col];
    }

    public int[] getLocationOfPersonInVehicle(Person p) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numSeatsPerRow[i]; j++) {
                if (personsOnBoard[i][j] != null && personsOnBoard[i][j].equals(p)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // Person not found
    }

    public Person[] getPeopleInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return null;
        }
        int count = 0;
        for (Person seat : personsOnBoard[row]) {
            if (seat != null) {
                count++;
            }
        }
        if (count == 0) {
            return null;
        }
        Person[] peopleInRow = new Person[count];
        int index = 0;
        for (Person seat : personsOnBoard[row]) {
            if (seat != null) {
                peopleInRow[index++] = seat.clone();
            }
        }
        return peopleInRow;
    }

    public Person[][] getPeopleOnBoard() {
        Person[][] clone = new Person[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            clone[i] = new Person[numSeatsPerRow[i]];
            for (int j = 0; j < numSeatsPerRow[i]; j++) {
                if (personsOnBoard[i][j] != null) {
                    clone[i][j] = personsOnBoard[i][j].clone();
                }
            }
        }
        return clone;
    }

    public boolean isPersonInVehicle(Person p) {
        for (Person[] row : personsOnBoard) {
            for (Person person : row) {
                if (person != null && person.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isPersonDriver(Person p) {
        return hasDriver() && getDriver().equals(p);
    }
}