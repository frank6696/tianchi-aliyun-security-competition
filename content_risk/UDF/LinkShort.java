import com.aliyun.odps.udf.UDF;
import java.io.FileReader;
import java.util.Scanner;

public final class LinkShort extends UDF {

    public static String get(String linksRaw) {
        String[] links = linksRaw.split("\\^");
        StringBuilder linkTexts = new StringBuilder("");

        int count = 0;
        for (String link : links) {
            if (link != null && link.length() <= 5) {
                linkTexts.append("," + link);
                count += 1;
            }
            if (count > 40) break;
        }

        String result;
        if (linkTexts != null) {
            result = linkTexts.toString();
            result = Helpers.process(result);
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
                    String linksraw = LinksRaw.get(html);
                    System.out.println("** linkshort ");
                    System.out.println(LinkShort.get(linksraw));
                }
            }
        } catch (Exception e) { System.out.printf(e.getMessage()); }
    }

}
