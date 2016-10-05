import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.Collections;


public class SAXManegador extends DefaultHandler {

    private boolean is_districte = false;
    public int nAccidents = 0;
    ArrayList<String> codidist = new ArrayList<>();


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nAccidents++;
        if (qName.equalsIgnoreCase("nomdistricte")) {
            is_districte = true;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;
        if (is_districte) {
            codidist.add(value);
            is_districte = false;
        }
    }

    public String getMax(ArrayList<String> sArr) {
        Collections.sort(sArr);
        String temp = sArr.get(1);
        String end = "";
        int max  = 0;
        int count = 0;
        for(String cad : sArr){
            if(cad.equals(temp)){
                count++;
            }
            else {
                if(count>=max){
                    max=count;
                    end=cad;
                }
                count = 0;
                temp = cad;
            }
        }

        return end + " amb " + max + " accidents.";
    }
        @Override
        public void startDocument () throws SAXException {
        }

        @Override
        public void endDocument () throws SAXException {

        }
    }
