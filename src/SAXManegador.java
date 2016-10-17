import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.Collections;


public class SAXManegador extends DefaultHandler {

    private boolean is_carrer = false;
    private boolean is_districte = false;
    private boolean is_dia = false;
    private boolean is_mes = false;
    private int nAccidents = 0;

    ArrayList<String> codidist = new ArrayList<>();
    ArrayList<String> carrers = new ArrayList<>();
    ArrayList<String> diaSetmana = new ArrayList<>();
    ArrayList<String> diaMes = new ArrayList<>();

    public int getnAccidents() {
        return nAccidents;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("nomdistricte")) {
            is_districte = true;
        } else if (qName.equalsIgnoreCase("nomcarrer"))
            is_carrer = true;
        else if (qName.equalsIgnoreCase("descripciodiasetmana"))
            is_dia = true;
        else if (qName.equalsIgnoreCase("nommes"))
            is_mes = true;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String value = new String(ch, start, length).trim();
        if (value.length() == 0) return;
        if (is_districte) {
            codidist.add(value);
            is_districte = false;
        } else if (is_carrer) {
            carrers.add(value);
            is_carrer = false;
        }else if (is_dia) {
            diaSetmana.add(value);
            is_dia = false;
        }else if (is_mes) {
            diaMes.add(value);
            is_mes = false;
        }
    }

    /**
     * recorre el array y busca el elemento mas repetido y cuantas veces esta repetido
     * @param sArr
     * @return l'element més repetit y la cuantitat
     */
    public String getMax(ArrayList<String> sArr) {
        Collections.sort(sArr);
        String temp = sArr.get(0);
        String end = "";
        int max = 0;
        int count = 0;

        for (String cad : sArr) {
            if (cad.equals(temp)) {
                count++;
            } else {
                count++;
                temp = cad;
                if (count > max) {
                    max = count;
                    end = temp;
                }
                count = 0;

            }
        }
        return end + " amb " + max + " accidents.";
    }

    /**
     * recorre el array y busca el elemento menos repetido y cuantas veces esta repetido
     * @param sArr
     * @return l'element més petit y la cuantitat
     */
    public String getMin(ArrayList <String> sArr) {
            Collections.sort(sArr);
            String temp = sArr.get(0);
            String end = "";
            int min = 20000;
            int count = 0;
            for (String cad : sArr) {
                if (cad.equals(temp)) {
                    count++;
                } else {
                    count++;
                    if (count <= min) {
                        min = count;
                        end = temp;
                    }
                    count = 0;
                    temp = cad;
                }
            }
            return end + " amb " + min + " accidents.";
        }

        @Override
        public void startDocument () throws SAXException {
        }

        @Override
        public void endDocument () throws SAXException {
            nAccidents = codidist.size();
        }
    }




