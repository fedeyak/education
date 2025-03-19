import java.util.LinkedList;
import java.util.TreeSet;

public class Pages {
    private final TreeSet<String> links = new TreeSet<>();
    private final LinkedList<String> visitedLinks = new LinkedList<>();

    public TreeSet<String> getLinks() {
        return links;
    }
    public void insertPage(String link) {
        this.links.add(link);
    }
    public boolean searchLinks(String link) {
        return this.links.contains(link);
    }
    public boolean searchVisitedLinks(String link) {
        return this.visitedLinks.contains(link);
    }
    public void addVisitedLink(String link) {
        this.visitedLinks.add(link);
    }
}

