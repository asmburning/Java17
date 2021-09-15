package feature;

import java.util.concurrent.ThreadLocalRandom;

/**
 * it is actually type auto cast
 */
public class PatternMatching {

    public static void main(String[] args) {
        Object o = randomObj();
        if (o instanceof String s){
            System.out.println(s.toLowerCase());
        }
    }

    private static Object randomObj(){
        if (ThreadLocalRandom.current().nextBoolean()) {
            return "TEST";
        }
        return 5;
    }
}
