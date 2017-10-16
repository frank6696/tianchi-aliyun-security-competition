import com.aliyun.odps.udf.UDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public final class LinksRaw extends UDF {

    public static String get(String html) {
        Document doc = Jsoup.parse(html);
        Elements links = doc.body().getElementsByTag("a");
        StringBuilder linkTexts = new StringBuilder("");

        for (Element link : links) {
            if (link.text() != null && !link.text().equals("")) {
                linkTexts.append("^" + link.text());
            }
        }

        String result;
        if (linkTexts != null) {
            result = linkTexts.toString();
        }
        else result = "none";
        return result;
    }

    public String evaluate(String s) {
        if (s == null) { return null;}
        String result = "none";
        try { result = get(s);
        } catch (Exception e) { }
        return result;
    }
}
