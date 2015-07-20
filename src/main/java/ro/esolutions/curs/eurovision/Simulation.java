package ro.esolutions.curs.eurovision;

import java.util.concurrent.Callable;

public interface Simulation extends Callable<String> {

	public void setSimulationConfiguration(SimulationConfiguration simulationConfiguration);

}
