import com.aliyun.odps.udf.UDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.FileReader;
import java.util.Scanner;

public final class Body extends UDF {

    // html -> body
    public static String get(String html) {
        String result = "none";
        Document doc = Jsoup.parse(html);
        doc.getElementsByAttributeValueMatching("style", ".*display:none.*").empty();
        Elements es = doc.body().getAllElements();
        StringBuilder sb = new StringBuilder("");

        for (Element e : es) {
            String text = e.ownText();
            if (text != null && text.length() > 6 && !e.tagName().equals("a")) {
                sb.append(text);
            }
            if (sb.length() > 2000) {
                sb.delete(2000, sb.length() - 1);
                break;
            }
        }
        result = sb.toString();
        result = Helpers.process(result);
        return result;
    }

    public String evaluate(String s) {
        if (s == null) { return null;}
        String result = "none";
        try { result = get(s); }
        catch (Exception e) { }
        return result;
    }

    public static void main(String[] args) {
        String[] filenames = Helpers.getFilenames(100);

        try {
            for (String name : filenames) {
                if (name == null) break;
                else {
                    System.out.println("\n" + name);
                    Scanner in = new Scanner(new FileReader(name));
                    StringBuilder sb = new StringBuilder("");
                    while (in.hasNextLine()) { sb.append(in.nextLine()); }
                    in.close();

                    String html = sb.toString();
                    System.out.println("** body ");
                    System.out.println(Body.get(html));
                }
            }
        } catch (Exception e) { System.out.printf(e.getMessage()); }

    }
}
