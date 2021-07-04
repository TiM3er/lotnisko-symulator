package smo.lot;

import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

/**
 * @author Dariusz Pierzchala
 */

public class DepartureEvent extends BasicSimEvent<Lotnisko, Object> {
    private SimGenerator generator;
    private Lotnisko parent;

    public DepartureEvent(Lotnisko parent, double delay) throws SimControlException {
        super(parent, delay);
        generator = new SimGenerator();
    }

    public DepartureEvent(Lotnisko parent) throws SimControlException {
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

        if (parent.samolotsonTheGround.size() > 0) {
            if (parent.runwayFree) {
                Samolot samolot = parent.samolotsonTheGround.get(0);
                parent.samolotsonTheGround.remove(samolot);
                parent.mvOnTheGround.setValue(parent.samolotsonTheGround.size(), parent.simTime());
                System.out.println(simTime() + " - Odleciał samolot: " + samolot.toString() + ". Na płycie altualnie jest: " + parent.samolotsonTheGround.size() + " samolot(ów)");
            }
            if (parent.samolotsonTheGround.size() > 0)
                parent.departureEvent = new DepartureEvent(parent, PlaneUtils.generateTime());
        }
    }

    @Override
    public Object getEventParams() {
        return null;
    }
}