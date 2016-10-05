import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;


public class SAXManegador extends DefaultHandler {

    Registre p = new Registre();
    List<Registre> Acc = new ArrayList<Registre>();


    private boolean is_districte = false;
    private String districte = "";
    public int nAccidents = 0;
    ArrayList<String> codidist = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nAccidents++;
        if (qName.equalsIgnoreCase("Codidistricte")) {
            is_districte = true;

        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Registre")) {
            Registre mew = new Registre();
            mew.setDistricte(districte);
            Acc.add(mew);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;
        if (is_districte) {
            districte = value;
            is_districte = false;
            codidist.add(value);
        }
    }
        @Override
        public void startDocument () throws SAXException {
        }

        @Override
        public void endDocument () throws SAXException {

        }
    }
