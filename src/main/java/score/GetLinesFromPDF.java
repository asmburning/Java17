package score;

import lombok.Value;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is an example on how to extract text line by line from pdf document
 */
public class GetLinesFromPDF extends PDFTextStripper {

    static List<String> lines = new ArrayList<String>();

    static Set<String> filterOutSet = new HashSet<>(16);

    static List<Student> students = new ArrayList(1024 * 4);

    static {
        filterOutSet.add("2022年襄阳市中考成绩条");
        filterOutSet.add("420625000101");
        filterOutSet.add("谷城县城关一中");
        filterOutSet.add("报名号");
        filterOutSet.add("考试号");
        filterOutSet.add("姓名");
        filterOutSet.add("总分");
        filterOutSet.add("语文");
        filterOutSet.add("数学");
        filterOutSet.add("英语");
        filterOutSet.add("物化");
        filterOutSet.add("政史");
        filterOutSet.add("音乐");
        filterOutSet.add("美术");
        filterOutSet.add("信技");
        filterOutSet.add("实验");
        filterOutSet.add("体育");
        filterOutSet.add("地生");
        filterOutSet.add("加分");
        filterOutSet.add("谷城县阳光学校");
        filterOutSet.add("谷城县五山二中");
        filterOutSet.add("谷城县五山镇中心学校");
        filterOutSet.add("谷城县茨河镇中心学校");
        filterOutSet.add("谷城县南河镇中心学校");
        filterOutSet.add("谷城县紫金镇中心学校");
        filterOutSet.add("谷城县庙滩二中");
    }

    public GetLinesFromPDF() throws IOException {
    }

    /**
     * @throws IOException If there is an error parsing the document.
     */
    public static void main(String[] args) throws IOException {
        PDDocument document = null;
        String fileName = "/Users/milesliu/Documents/score.pdf";
        try {
            document = PDDocument.load(new File(fileName));
            PDFTextStripper stripper = new GetLinesFromPDF();
            stripper.setSortByPosition(true);
            stripper.setStartPage(0);
            stripper.setEndPage(document.getNumberOfPages());

            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);

            for (int i = 0; i < lines.size() - 2; i += 3) {
                String name = lines.get(i + 1);
                if (name.length() == 1) {
                    name += lines.get(i + 2);
                    i = i + 1;
                }
                String scores = lines.get(i + 2);
                int score = parseScore(scores);
                students.add(new Student(name, score));

            }


            students.sort((s1, s2) -> {
                return s2.getScore() - s1.getScore();
            });

            Set<String> checkNames = Set.of("常慧丽", "刘晨阳", "刘勇琦", "沈家伟");
            System.out.println(students.size());
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (checkNames.contains(student.getName())) {
                    System.out.print(i + "  ");
                    System.out.println(student);
                }
                if (student.getScore()< 59110){
                    System.out.print(i + "  ");
                    System.out.println(student);
                    break;
                }
            }

        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    private static int parseScore(String scores) {
        String[] split = scores.split(" ");
        String score = split[0];
        try {
            Double parseDouble = Double.parseDouble(score);
            Double expand = parseDouble * 100;
            return expand.intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Override the default functionality of PDFTextStripper.writeString()
     */
    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {
        if (!filterOutSet.contains(str) && !str.contains("谷城县") && !str.contains("420625")) {
            lines.add(str);
        }
    }

    @Value
    static class Student implements Comparable {

        String name;
        int score;

        @Override
        public int compareTo(Object o) {
            Student s = (Student) o;
            return this.score - s.score;
        }
    }
}