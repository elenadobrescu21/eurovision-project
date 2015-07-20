package ro.esolutions.curs.eurovision;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.apache.log4j.Logger;

public class EurovisionDummyImplementation implements Simulation {

	static Logger logger = Logger.getLogger(EurovisionDummyImplementation.class);

	private SimulationConfiguration simulationConfiguration;

	public EurovisionDummyImplementation(SimulationConfiguration simulationConfiguration) {
		Objects.requireNonNull(simulationConfiguration, "simulationConfiguration must not be null");
		this.simulationConfiguration = simulationConfiguration;
	}

	public void setSimulationConfiguration(SimulationConfiguration simulationConfiguration) {
		Objects.requireNonNull(simulationConfiguration, "simulationConfiguration must not be null");
		this.simulationConfiguration = simulationConfiguration;
	}
	
	/**
	 * This method (required by the Callable interface) should return the winning country.
	 * (which will organize next year the Eurovision contest)
	 */
	public String call() throws Exception {
		
		logger.info(simulationConfiguration);

		try {
			// create dummy empty files (instead of real CSV output)
			new File(simulationConfiguration.getFilenameFinal()).createNewFile();
			new File(simulationConfiguration.getFilenameFirstSemifinal()).createNewFile();
			new File(simulationConfiguration.getFilenameSecondSemifinal()).createNewFile();
		} catch (IOException e) {
			logger.error(e);
			throw e;
		}
		// return a dummy result
		return "Norway";
	}

}
