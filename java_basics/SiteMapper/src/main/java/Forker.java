import org.jsoup.select.Elements;

import java.util.concurrent.RecursiveAction;

public class Forker extends RecursiveAction {

    private final Elements links;
    private final SiteMapper siteMapper;

    public Forker(Elements elements, SiteMapper siteMapper) {
        this.links = elements;
        this.siteMapper = siteMapper;
    }

    @Override
    protected void compute() {
        if (links.size() < 1) {
            return;
        }
        else if (links.size() < 2) {
            siteMapper.handleLink(links.get(0));
        } else {
            int halfSize = links.size() / 2;
            Elements firstHalf = cutElements(0, halfSize);
            Elements secondHalf = cutElements(halfSize, links.size());

            invokeAll(new Forker(firstHalf, siteMapper),
                    new Forker(secondHalf, siteMapper));
        }
    }
    private Elements cutElements(int start, int end) {
        Elements halfElements = new Elements();
        for (int i = start; i < end; i++) {
            halfElements.add(links.get(i));
        }
        return halfElements;
    }
}