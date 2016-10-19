import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;


public class SAXManegador extends DefaultHandler {

    private boolean is_carrer = false;
    private boolean is_districte = false;
    private boolean is_dia = false;
    private boolean is_mes = false;
    private int nAccidents = 0;

    List<String> codidist = new ArrayList<>();
    List<String> carrers = new ArrayList<>();
    List<String> diaSetmana = new ArrayList<>();
    List<String> diaMes = new ArrayList<>();

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

    /**
     * comprovem quina dada volem y la afegim a un array
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
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
        } else if (is_dia) {
            diaSetmana.add(value);
            is_dia = false;
        } else if (is_mes) {
            diaMes.add(value);
            is_mes = false;
        }
    }


    /**
     * compara cada cadena diferent y emmagatzema la que te major freqüència
     * @param lista
     * @return nom + freqüència
     */
    public String getMax(List<String> lista) {
        Set<String> hash = new HashSet<String>(lista);
        int temp = 0;
        String cadTemp = null;
        for (String cadena : hash) {
            if (Collections.frequency(lista, cadena) >= temp) {
                temp = Collections.frequency(lista, cadena);
                cadTemp = cadena;
            }
        }
        return (cadTemp + " amb " + temp + " Accidents.");
    }

    /**
     * Compara cada cadena diferent y emmagatzema la que té menor freqüència
     * @param lista
     * @return nom + freqüència
     */
    public String getMin(List<String> lista) {
        Set<String> hash = new HashSet<String>(lista);
        int temp = 20000;
        String cadTemp = null;
        for (String cadena : hash) {
            if (Collections.frequency(lista, cadena) <= temp) {
                temp = Collections.frequency(lista, cadena);
                cadTemp = cadena;
            }
        }
        return (cadTemp + " amb " + temp + " Accidents.");
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
        nAccidents = codidist.size();
    }
}




