package feature;

public class StringEnhance {

    public static void main(String[] args) {
        String multiLine = """
                this is the first line, you can't add any text after three double quotes
                this is the seconds line
                you can have something before the end""";
        System.out.println(multiLine);
    }
}
