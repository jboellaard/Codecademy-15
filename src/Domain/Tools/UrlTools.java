package Domain.Tools;

public class UrlTools {

    // URL’s hebben de volgende vorm: <https:// of http://><minimaal één let-
    // ter>.<minimaal één letter>.<minimaal één letter>

    public static boolean formatUrl(String url) {
        url = url.trim();
        if (!url.contains("https://") || !url.contains("http://")) {
            return false;
        } else if (url.split("//")[1].length() < 1) {
            return false;
        } else if (url.split("\\.")[1].split("\\.")[0].length() < 1) {
            return false;
        } else if (url.split("\\.")[1].split("\\.")[1].length() < 1) {
            return false;
        }
        return true;
    }
}
