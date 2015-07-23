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

	public static void main(String args[]) throws Exception {
		int cores = Runtime.getRuntime().availableProcessors();
		logger.info("number of available cores: " + cores);
		ExecutorService executorService = Executors.newFixedThreadPool(cores);
		Future<String> winner2015 = executorService.submit(new Eurovision());
		executorService.execute((Runnable) winner2015);
		logger.info(winner2015.get());
		executorService.shutdown();
	}
}

