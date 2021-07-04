package smo.lot;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.util.LinkedList;
import java.util.List;

public class Lotnisko extends BasicSimObj {
    //	int inTheAir; // liczba samolotów w powietrzu
//	int onTheGround; // liczba samolotów czekających na lotnisku
    boolean runwayFree; // dostępność pasa lądowania
    double arrivalInterval; // okres pomiedzy kolejnymi przylotami
    //int arrivalTime; // czas następnego przylotu
    ArrivalEvent arrivalEvent;
    List<Samolot> samolotsinTheAir;
    List<Samolot> samolotsonTheGround;
    List<Samolot> samolotsdepartureEvent;
    //	int arrivalEventPriority;
    //int landingTime; // czas zakończenia lądowania
    LandingEvent landingEvent;
    double landingDuration; // czas trwania lądowania
    double departureInterval; // okres pomiędzy odlotami
    //int departureTime; // czas następnego odlotu
    DepartureEvent departureEvent;
    MonitoredVar mvOnTheGround, mvInTheAir, mvRunwayFree;

    public Lotnisko(int arrivalInterval, int landingDuration, int departureInterval, int period, SimManager simMgr)
            throws SimControlException {
        this.samolotsinTheAir = new LinkedList<>();
        this.samolotsonTheGround = new LinkedList<>();
        this.samolotsdepartureEvent = new LinkedList<>();
//		this.inTheAir = 0;
//		this.onTheGround = 0;
        this.runwayFree = true;
        this.arrivalInterval = arrivalInterval;
        this.landingDuration = landingDuration;
        this.departureInterval = departureInterval;
        mvOnTheGround = new MonitoredVar(1);
        mvInTheAir = new MonitoredVar(0);
        mvRunwayFree = new MonitoredVar(0);

//		arrivalEventPriority = SimParameters.MaxSimPriority;
        arrivalEvent = new ArrivalEvent(this, 1);
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }

    public Samolot getSamalot() {
        Samolot samolotLanding = samolotsinTheAir.get(0);
        for (Samolot samolot : samolotsinTheAir) {
            if (samolot.getPriority() == 0) {
                samolotsinTheAir.remove(samolot);
                return samolot;
            }
        }
        mvInTheAir.setValue(samolotsinTheAir.size(), simTime());
        return samolotLanding;
    }
}
