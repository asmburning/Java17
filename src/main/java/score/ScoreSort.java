package score;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreSort {


    /**
     * @throws IOException If there is an error parsing the document.
     */
    public static void main(String[] args) throws IOException {
        PDDocument doc = PDDocument.load(new File("/Users/milesliu/Documents/score.pdf"));
        PDFTextStripper textStripper = new PDFTextStripper();
        //textStripper.setArticleEnd("加分");
        String text = textStripper.getText(doc);
        String[] lines = text.split("\r\n|\r|\n");

        for (int i = 0; i < 140; i++) {
            System.out.println(lines[i]);
        }

    }


}
