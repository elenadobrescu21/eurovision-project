package ro.esolutions.curs.eurovision;

import java.util.Objects;

import org.apache.log4j.Logger;

public class Eurovision implements Simulation {

	static Logger logger = Logger.getLogger(Eurovision.class);

	private SimulationConfiguration simulationConfiguration;

	public Eurovision(SimulationConfiguration simulationConfiguration) {
		Objects.requireNonNull(simulationConfiguration, "simulationConfiguration must not be null");
		this.simulationConfiguration = simulationConfiguration;
	}

	public void setSimulationConfiguration(SimulationConfiguration simulationConfiguration) {
		Objects.requireNonNull(simulationConfiguration, "simulationConfiguration must not be null");
		this.simulationConfiguration = simulationConfiguration;
	}

	/**
	 * This method (required by the Callable interface) should return the
	 * winning country. (which will organize next year the Eurovision contest)
	 */
	public String call() throws Exception {
		logger.info(simulationConfiguration);

		// TODO implement simulation here
		// ...

		// TODO this should return a non-null result - the winning country !
		return null;
	}

}
