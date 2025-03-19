import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.concurrent.ForkJoinPool;
public class SiteMapper {

    private final Pages pages;
    private final String websiteAddress;
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public SiteMapper(String websiteAddress, Pages pages) {
        this.websiteAddress = websiteAddress;
        this.pages = pages;
        siteMapperEngine(websiteAddress);
    }

    private void siteMapperEngine(String url) {
        this.pages.addVisitedLink(url);
        Elements links = parseLinks(url);
        Forker task = new Forker(links, this);
        forkJoinPool.invoke(task);
    }

    public void handleLink(Element link) {
        String cleanLink = cleanLink(link);
        if (linkChecker(cleanLink)) {
            if (cleanLink.charAt(cleanLink.length() - 1) != '/') cleanLink = cleanLink + '/';
            this.pages.insertPage(cleanLink);
            if (!this.pages.searchVisitedLinks(cleanLink)) {
                siteMapperEngine(cleanLink);
            }
        }
    }

    private Elements parseLinks(String url) {
        Connection connection = Jsoup.connect(url)
                .timeout(25000)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .referrer(url)
                .followRedirects(false);
        try {
            Document doc = connection.get();
            return doc.select("a[href]");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String cleanLink(Element link) {
        return link.attr("abs:href")
                .replace(" ", "")
                .replace("#", "")
                .replace("%20", "");
    }

    private boolean linkChecker(String cleanLink) {
        return !this.pages.searchLinks(cleanLink) &&
                cleanLink.contains(this.websiteAddress) &&
                !cleanLink.contains(".jpg") &&
                !cleanLink.contains(".png") &&
                !cleanLink.contains(".pdf") &&
                !cleanLink.contains(".gif") &&
                !cleanLink.contains(".js") &&
                !cleanLink.contains(".css") &&
                !cleanLink.contains("mailto") &&
                !cleanLink.contains("page=inquire");
    }
}

