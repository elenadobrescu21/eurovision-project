package ro.esolutions.curs.eurovision;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

// SIMULATION OUTPUT - CSV format for each of 2 x Semifinal + Final

// "COUNTRY", Participant1, Participant2, ..., Participant M
// Voter1, 12, 1, 10, ...
// Voter2, 1, 8, 7, ...
// ....................
// Voter N, ...........
// "TOTAL", .............
// "ARTIST", Artist1, ... Artist M
// "TITLE", Title1, .....Title M

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	public static void main(String args[]) {
		
		// TODO use the number of available cores to restrict the number of Threads 
		// which are running at the same time in your simulation (see Executors... )
		int cores = Runtime.getRuntime().availableProcessors();
		logger.info("number of available cores: " + cores);

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		try {
			
			//TODO replace EurovisionDummyImplementation with your Eurovision class
			
			Future<String> winner2015 = executorService
					.submit(new EurovisionDummyImplementation(createDummyConfiguration("Sweden", 2015)));
			Future<String> winner2016 = executorService.submit(new EurovisionDummyImplementation(createDummyConfiguration(winner2015.get(), 2016)));
			executorService.submit(new EurovisionDummyImplementation(createDummyConfiguration(winner2016.get(), 2017)));
		} catch (InterruptedException | ExecutionException e) {
			logger.error(e);
		}
		executorService.shutdown();
	}

	//TODO create real configurations (more JSONs, more countries etc.)
	static public SimulationConfiguration createDummyConfiguration(String favorite, int year) {
		
		//creates dummy CSV files with bet ratings (to be used as inputs for the simulation)
		String inputCsvFilename = "eurovision-" + year + "-bets.csv";
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(inputCsvFilename);
			pw.println(String.format("COUNTRY,RATING"));
			
			// add some dummy data
			pw.println(String.format("%s,3", favorite));
			pw.println(String.format("Ireland,5"));
			pw.println(String.format("Spain,9.5"));
			pw.println(String.format("Germany,7"));
			pw.println(String.format("Italy,18"));
			
		} catch (FileNotFoundException e) {
			logger.error(e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		return new SimulationConfiguration(inputCsvFilename, Arrays.asList("MediaProMusic.json"),
				Arrays.asList("Spain"), 1, "eurovision-" + year + "-semifinal-1.csv",
				"eurovision-" + year + "-semifinal-2.csv", "eurovision-" + year + "-final.csv");
	}
}
