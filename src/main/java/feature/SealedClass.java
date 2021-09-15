package feature;

public class SealedClass {

    public static void main(String[] args) {
        Transfer transfer = new Car(5, "car");
        System.out.println(transfer);
    }


    sealed interface Transfer permits Car, Truck {

        int getMaxServiceIntervalInMonths();

        default int getMaxDistanceBetweenServicesInKilometers() {
            return 100000;
        }

    }


    abstract static sealed class Vehicle permits Car, Truck {

        protected final String registrationNumber;

        public Vehicle(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public String getRegistrationNumber() {
            return registrationNumber;
        }

    }

    final static class Truck extends Vehicle implements Transfer {

        private final int loadCapacity;

        public Truck(int loadCapacity, String registrationNumber) {
            super(registrationNumber);
            this.loadCapacity = loadCapacity;
        }

        public int getLoadCapacity() {
            return loadCapacity;
        }

        @Override
        public int getMaxServiceIntervalInMonths() {
            return 18;
        }

    }

    non-sealed static class Car extends Vehicle implements Transfer {

        private final int numberOfSeats;

        public Car(int numberOfSeats, String registrationNumber) {
            super(registrationNumber);
            this.numberOfSeats = numberOfSeats;
        }

        public int getNumberOfSeats() {
            return numberOfSeats;
        }

        @Override
        public int getMaxServiceIntervalInMonths() {
            return 12;
        }

    }

}
