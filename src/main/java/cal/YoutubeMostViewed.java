package cal;

import lombok.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * go to videos tab and load all videos, then save as html
 */
public class YoutubeMostViewed {


    public static void main(String[] args) throws Exception{
        File input = new File("/Users/milesliu/Downloads/JFlaMusic - YouTube.html");
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        //Document doc = Jsoup.connect("http://example.com").get();
        Elements elements = doc.select("#details");
        List<Video> videoList = elements.stream()
                .map(element -> toVideoInfo(element))
                .filter(video -> video.getViewed() > 1_000_000)
                .sorted()
                .collect(Collectors.toList());
        int endIndex = Math.min(40, videoList.size());
        for (int i = 0; i < endIndex ; i++) {
            System.out.println(videoList.get(i));
        }
    }


    private static Video toVideoInfo(Element element){
        Elements detail = element.select("#video-title");
        String title = detail.attr("title");
        String viewed = detail.attr("aria-label");

        int viewsIndex = viewed.lastIndexOf("views");
        viewed = viewed.substring(0, viewsIndex - 1);

        int startIndex = viewed.lastIndexOf(" ");
        String substring = viewed.substring(startIndex + 1);
        substring = substring.replaceAll(",", "");
        int viewCount = Integer.parseInt(substring);
        return new Video(viewCount, title);
    }

    @Value
    static class Video implements Comparable{
        int viewed;
        String title;

        @Override
        public int compareTo(Object o) {
            Video video = (Video) o;
            return video.getViewed() - this.viewed;
        }
    }
}
