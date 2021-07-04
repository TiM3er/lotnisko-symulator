package smo.lot;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * @author Dariusz Pierzchala
 */
public class LandingEvent extends BasicSimEvent<Lotnisko, Object> {
    private SimGenerator generator;
    private Lotnisko parent;
    private Samolot samolot;

    public LandingEvent(Lotnisko parent, double delay) throws SimControlException {
        super(parent, delay);
        generator = new SimGenerator();
    }

    public LandingEvent(Lotnisko parent, double delay, Samolot samolot) throws SimControlException {
        super(parent, delay);
        this.samolot = samolot;
        generator = new SimGenerator();
    }

    public LandingEvent(Lotnisko parent) throws SimControlException {
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
        parent.samolotsonTheGround.add(this.samolot);
        parent.mvOnTheGround.setValue(parent.samolotsonTheGround.size(), parent.simTime());
        System.out.println(simTime() + " - Wylądował samolot: " + samolot.toString() + " Na płycie altualnie jest: " + parent.samolotsonTheGround.size() + " a w powietrzu " + parent.samolotsinTheAir.size() + " samolot(ów)");
        parent.runwayFree = true;
        parent.mvRunwayFree.setValue(1, parent.simTime());
        System.out.println(simTime() + " - Zwolniono pas lądowania");
        if (parent.samolotsinTheAir.size() > 0) {
            if (parent.samolotsonTheGround.size() > 0)
                parent.departureEvent = new DepartureEvent(parent, PlaneUtils.generateTime());
            if (parent.samolotsinTheAir.size() > 0) {
                parent.landingEvent = new LandingEvent(parent, PlaneUtils.generateTime(), parent.getSamalot());
                System.out.println(simTime() + " - Zaplanowano lądowanie");
            }
        }
    }

    @Override
    public Object getEventParams() {
        return null;
    }
}