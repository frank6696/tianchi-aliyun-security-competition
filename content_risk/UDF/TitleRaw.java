import com.aliyun.odps.udf.UDF;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public final class TitleRaw extends UDF {

    // html -> title raw
    public static String get(String html) {
        String result = "none";
        Document doc = Jsoup.parse(html);
        if (doc.title() != null) result = doc.title();
        return result;
    }

    public String evaluate(String s) {
        if (s == null) { return null;}
        String result = "none";
        try { result = get(s);
        } catch (Exception e) { }
        return result;
    }

    public static void main(String[] args) { }
}
