
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    public static void main(String[] args) throws Exception {
//        String fileName = "res/data-0.2M.xml";
//        String fileName = "res/data-18M.xml";
        String fileName = "res/data-1572M.xml";

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();

        long start = System.currentTimeMillis();
        parser.parse(new File(fileName), handler);
        System.out.println("Parsing duration: " + (System.currentTimeMillis() - start));
    }
}