import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Helpers {

    // 将 reg 在 text 中的匹配 替换为 subst, 忽略大小写.
    public static String replace(String text, String reg, String subst) {
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.replaceAll(subst);
    }

    // 对文字进行处理, 替换网址/电话等 token, 等等
    public static String process(String text) {
        if (text == null || text.equals("")) return "";
        String result = text;

        // 网址, 邮箱
        String reg_url = "(www|http)[^\\s\\u4e00-\\u9fa5]*";
        result = replace(result, reg_url, " aurl ");
        String reg_mail = "\\\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        result = replace(result, reg_mail, " amail ");

        // 英文句号
        result = result.replaceAll("\\.", "");

        // 空格
        result = result.replaceAll("[\\s\\u3000]+","");

        // 11~7 位数字
        result = replace(result, "1\\d{10}", " aphone ");
        result = replace(result, "\\d{10}", " dten ");
        result = replace(result, "\\d{9}", " dnine ");
        result = replace(result, "\\d{8}", " deight ");
        result = replace(result, "\\d{7}", " dseven ");

        // 含符号的手机号码
        result = replace(result, "1\\d{2}.\\d{4}.\\d{4}", " aphone ");

        // 6~1 位数字
        result = replace(result, "\\d{6}", " dsix ");
        result = replace(result, "\\d{5}", " dfive ");
        result = replace(result, "20[01]\\d{1}", " ayear ");
        result = replace(result, "\\d{4}", " dfour ");
        result = replace(result, "\\d{3}", " dthree ");
        result = replace(result, "\\d{2}", " dtwo ");
        result = replace(result, "\\d{1}", " donee ");

        // 时间和日期
        result = result.replaceAll(" ayear [-/] (dtwo|donee) [-/] (dfour|dthree|dtwo) : (dtwo|donee) : (dtwo|donee) ", " atime ");
        result = result.replaceAll(" ayear [-/] (dtwo|donee) [-/] (dfour|dthree|dtwo) : (dtwo|donee) ", " atime ");
        result = result.replaceAll(" ayear [-/] (dtwo|donee) [-/] (dtwo|donee) ", " adate ");
        result = result.replaceAll(" ayear [-/] (dtwo|donee) [-/] (dtwo|donee) ", " adate ");

        return result;
    }

    public static String[] getFilenames(int limit) {
        File file = new File("./");
        File[] files = file.listFiles();
        String[] filenames = new String[100];
        int i = 0;
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".html")) {
                filenames[i] = f.getName();
                i += 1;
                if (i >= limit) break;
            }
        }
        return filenames;
    }
    public static void main(String[] args) {
        String text = "TEL: 134a9909a3345, 2011, 2032";
        System.out.printf(process(text));
    }
}
