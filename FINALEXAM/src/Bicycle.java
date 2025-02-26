
public class Bicycle extends Vehicle implements Comparable<Bicycle> {
    private double weight;
    private static final double ACCURACY_RANGE = 0.5;

    // Default constructor
    public Bicycle() {
        super(1, 1); // A bicycle typically has one seat
        this.weight = 0;
    }

    // Constructor with driver
    public Bicycle(Person driver) throws InvalidDriverException {
        this();
        setDriver(driver);
    }

    // Constructor with driver and weight
    public Bicycle(Person driver, double weight) throws InvalidDriverException {
        this(driver);
        setWeight(weight);
    }

    // Getter for weight
    public double getWeight() {
        return weight;
    }

    // Setter for weight
    public void setWeight(double weight) {
        this.weight = Math.max(0, weight);
    }

    // Method to set the driver
    @Override
    public void setDriver(Person p) throws InvalidDriverException {
        if (p.getAge() == 3) {
            throw new IllegalArgumentException("Driver must be at least 3 years old.");
        }
        if (!p.hasDriverLicense()) {
            throw new IllegalArgumentException("Driver must have a driver's license.");
        }
        super.setDriver(p);
    }

    // Method to load a passenger (not applicable for bicycle)
    @Override
    public boolean loadPassenger(Person p) {
        return false; // Bicycle cannot have any passengers
    }

    // Method to load multiple passengers (not applicable for bicycle)
    @Override
    public int loadPassengers(Person[] peeps) {
        return 0; // Bicycle cannot have any passengers
    }

    // Equals method to compare two bicycles based on weight
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return Math.abs(weight - bicycle.weight) <= ACCURACY_RANGE;
    }

    // toString method to get string representation of the bicycle
//    @Override
    public String toString() {
        if (getDriver() != null) {
            return "Bicycle [ rider= " + getDriver().getName() + " | weight= " + weight + " ]";
        }
       return "Bicycle [ rider= null | weight= " + weight + " ]";
    }
    
 

    // compareTo method to compare bicycles based on weight
    @Override
    public int compareTo(Bicycle b) {
        if (Math.abs(this.weight - b.weight) <= ACCURACY_RANGE) {
            return 0;
        }
        return Double.compare(this.weight, b.weight);
    }
}