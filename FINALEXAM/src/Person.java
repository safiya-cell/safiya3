import java.util.Objects;

public class Person implements Cloneable {
    private String name;
    private boolean hasDriverLicense;
    private int age;
    private int height;

    // Constructor
    public Person(String name, boolean hasDriverLicense, int age, int height) {
        this.name = name;
        this.hasDriverLicense = hasDriverLicense;
        this.age = age;
        this.height = height;
    }

    // Getters
    public String getName() {
        return name;
    }

    public boolean hasDriverLicense() {
        return hasDriverLicense;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return hasDriverLicense == person.hasDriverLicense &&
               age == person.age &&
               height == person.height &&
               Objects.equals(name, person.name);
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(name, hasDriverLicense, age, height);
    }

    // Overriding toString method
    @Override
    public String toString() {
        String licenseStatus = hasDriverLicense ? "has license" : "no license";
        return String.format("Person [name= %10s | age= %02d | height= %02d | %s]", name, age, height, licenseStatus);
    }

    // Implementing clone method
    @Override
    public Person clone() {
        try {
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }
}