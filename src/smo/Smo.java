package smo;
/**
 * @author Dariusz Pierzchala
 * <p>
 * Description: Description: Klasa gniazda obsługi obiektów - zgłoszeń
 */

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.LinkedList;


public class Smo extends BasicSimObj {
    private LinkedList<Zgloszenie> kolejka;
    private LinkedList<Zgloszenie> utylizator;
    private boolean wolne = true;
    public RozpocznijObsluge rozpocznijObsluge;
    public ZakonczObsluge zakonczObsluge;
    private int ilosc_miejsc; //ograniczenie kolejki
    private boolean status = false; // ustawia tryb obslugi z ograniczeniem lub bez
    private int ilZglPoprawnych = 0; //licznik udanych / nieudanych zgloszen

    /**
     * Creates a new instance of Smo
     *
     * @throws SimControlException
     */
    public Smo() throws SimControlException {
        // Utworzenie wewnętrznej listy w kolejce
        kolejka = new LinkedList<Zgloszenie>();
        utylizator = new LinkedList<Zgloszenie>();
    }

    public Smo(int ilosc_miejsc) throws SimControlException {
        // Utworzenie wewnętrznej listy w kolejce
        kolejka = new LinkedList<Zgloszenie>();
        utylizator = new LinkedList<Zgloszenie>();
        status = true;
        this.ilosc_miejsc = ilosc_miejsc;
    }

    // Wstawienie zgłoszenia do kolejki
    public boolean dodaj(Zgloszenie zgl) {

        if (status) {
            if (kolejka.size() < ilosc_miejsc) {
                ilZglPoprawnych++;
                kolejka.add(zgl.getPriority(), zgl);
                System.out.println("\nStan kolejki " + kolejka.size() + "/ " + ilosc_miejsc);
            } else {

                System.out.println("\nNie mozna dodac do kolejki zgloszenia " + zgl.tenNr +  "  Stan kolejki " + kolejka.size() + "/ " + ilosc_miejsc);
                utylizator.add(zgl.getPriority(), zgl);
                System.out.println("Zgloszenie dodane do utylizatora\n");

                return false;
            }
        } else {
            kolejka.add(zgl.getPriority(), zgl);
        }
        System.out.println("\nIlość obslużonych zgloszeń: " + ilZglPoprawnych);
        System.out.println("Ilosc zgłoszeń w utylizatorze " + utylizator.size() + "\n");

        return true;
    }

    //usuwa element o największym priorytecie
    public Zgloszenie removeFirstElem() {
        Zgloszenie max = kolejka.getFirst();
        for (Zgloszenie zglosz : kolejka) {
            if (zglosz.getPriority() > max.getPriority()) {
                max = zglosz;
            }

        }
        kolejka.remove(max);
        return max;
    }

    // Pobranie zgłoszenia z kolejki
    public Zgloszenie usun() {

        Zgloszenie zgl = (Zgloszenie) removeFirstElem();
        return zgl;
    }

    // Pobranie zgłoszenia z kolejki
    public boolean usunWskazany(Zgloszenie zgl) {
        Boolean b = kolejka.remove(zgl);
        return b;
    }

    public int liczbaZgl() {
        return kolejka.size();
    }

    public boolean isWolne() {
        return wolne;
    }

    public void setWolne(boolean wolne) {
        this.wolne = wolne;
    }

    @Override
    public void reflect(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean filter(IPublisher publisher, INotificationEvent event) {
        // TODO Auto-generated method stub
        return false;
    }
}