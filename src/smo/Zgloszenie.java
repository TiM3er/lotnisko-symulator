package smo;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

/**
 * Description: Klasa zgloszenia obsługiwanego w gnieździe obsługi.
 *
 * @author Dariusz Pierzchala
 */

public class Zgloszenie extends BasicSimObj {
    double czasOdniesienia;
    static int nr = 0;
    int tenNr;
    public Smo smo;
    public int priorytet;


    public Zgloszenie(double Czas, Smo smo) throws SimControlException {
        czasOdniesienia = Czas;
        setTenNr();
        this.smo = smo;
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

    public void setTenNr() {
        this.tenNr = nr++;
    }

    public int getTenNr() {
        return tenNr;
    }

    public void setPriority(int priorytet) {
        this.priorytet = priorytet;
    }

    public int getPriority() {
        return priorytet;
    }




}