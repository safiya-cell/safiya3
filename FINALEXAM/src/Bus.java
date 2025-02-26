
public class Bus extends Car {
    // Constructors
    public Bus(int[] numSeatsPerRow) {
        super(2, (2 * numSeatsPerRow.length) - 1, numSeatsPerRow); // 2 doors, windows calculated
    }

    public Bus(Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        super(2, (2 * numSeatsPerRow.length) - 1, driver, numSeatsPerRow);
    }

    // Method to check if a person can open a door
    @Override
    public boolean canOpenDoor(Person p) {
        int[] location = getLocationOfPersonInVehicle(p);
        if (location[0] == -1) {
            return false;
        }
        int row = location[0];
        return isPersonDriver(p) || (row == numberOfRows - 1 && p.getAge() > 5 && p.getHeight() > 40);
    }

    // Method to check if a person can open a window
    @Override
    public boolean canOpenWindow(Person p) {
        return super.canOpenWindow(p) && p.getAge() > 5;
    }

    // Properly overridden toString method
    @Override
    public String toString() {
        return "Bus is an extension of " + super.toString();
    }

    // Announcements methods
    @Override
    public String departure() {
        return super.departure() + "The Bus\n";
    }

    @Override
    public String arrival() {
        return super.arrival() + "Of The Bus\n";
    }

    @Override
    public String doNotDisturbTheDriver() {
        return super.doNotDisturbTheDriver() + "On The Bus\n";
    }
}