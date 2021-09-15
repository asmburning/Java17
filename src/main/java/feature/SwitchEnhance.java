package feature;

public class SwitchEnhance {

    public static void main(String[] args) {
        printResult(Week.Monday);
        printResult(Week.Friday);
    }

    private static void printResult(Week week){
        switch (week){
            case Monday -> System.out.println("Blue Monday");
            case Friday -> System.out.println("Happy Friday");
        }
    }

    enum Week{
        Monday,Friday
    }
}
