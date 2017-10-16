import com.aliyun.odps.udf.UDF;

public final class Title extends UDF {

    // title_raw -> title
    public static String get(String raw) {
        return Helpers.process(raw);
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
