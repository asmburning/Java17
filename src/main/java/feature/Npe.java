package feature;

/**
 * NPE have more detailed error message
 * default enabled in Java17
 */
public class Npe {

    public static void main(String[] args) {
        String a =null;
        a.toLowerCase();
    }
}
