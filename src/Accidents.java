import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Accidents {
    public static void main(String[] args) throws IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        InputStream xmlInput = new FileInputStream("accidents.xml");

        try {

            SAXParser saxParser = factory.newSAXParser();
            SAXManegador handler = new SAXManegador();
            saxParser.parse(xmlInput, handler);
            tractarllista(handler);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    private static void tractarllista(SAXManegador sax) {

        for (Registre reg : sax.Acc)

        System.out.println("Número accidents: " + sax.nAccidents);
    }
}