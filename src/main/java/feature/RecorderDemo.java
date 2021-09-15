package feature;

public class RecorderDemo {

    public static void main(String[] args) {
        Person person = new Person("Jacky", "Caribbean");
        Location address = new Location("Caribbean");

        System.out.println(person);
        System.out.println(address);
    }

    public record Person (String name, String address) {}

    record Location ( String address) {}
}
