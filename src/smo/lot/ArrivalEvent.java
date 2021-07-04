package smo.lot;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * @author Dariusz Pierzchala
 */

public class ArrivalEvent extends BasicSimEvent<Lotnisko, Object> {
    private SimGenerator generator;
    private Lotnisko parent;

    public ArrivalEvent(Lotnisko parent, double delay) throws SimControlException {
        super(parent, delay);
        generator = new SimGenerator();
    }

    public ArrivalEvent(Lotnisko parent, double delay, int priorytet) throws SimControlException {
        super(parent, delay, priorytet);
        generator = new SimGenerator();
    }

    public ArrivalEvent(Lotnisko parent) throws SimControlException {
        super(parent);
        generator = new SimGenerator();
    }

    @Override
    protected void onInterruption() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onTermination() throws SimControlException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void stateChange() throws SimControlException {
        parent = getSimObj();
        Samolot samolot = new Samolot(PlaneUtils.generatePrority(), PlaneUtils.generateId());
        parent.samolotsinTheAir.add(samolot);
        parent.mvInTheAir.setValue(parent.samolotsinTheAir.size(), parent.simTime());
        System.out.println(simTime() + " - Przybył samolot " + samolot.toString() + ". Nad lotniskiem altualnie jest: " + parent.samolotsinTheAir.size() + " samolot(ów)");
        // Wygeneruj czas do kolejnego przylotu
        // parent.arrivalInterval = generator.normal(5.0, 1.0);
        parent.arrivalEvent = new ArrivalEvent(parent, PlaneUtils.generateTime());
        //planowanie lądowania
        if (parent.runwayFree) {

            // landingTime = (int) simTime() + landingDuration;
            if (this.parent.samolotsinTheAir.size() > 0) {
                parent.runwayFree = false;
                parent.mvRunwayFree.setValue(0, parent.simTime());
                System.out.println(simTime() + " - Zaplanowano lądowanie");
                parent.landingEvent = new LandingEvent(parent, PlaneUtils.generateTime(), parent.getSamalot());
            }
        }
    }

    @Override
    public Object getEventParams() {
        return null;
    }


}