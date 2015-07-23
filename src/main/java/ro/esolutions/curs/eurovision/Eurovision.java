package ro.esolutions.curs.eurovision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Eurovision implements Simulation {

	static Logger logger = Logger.getLogger(Eurovision.class);

	private SimulationConfiguration simulationConfiguration;
	private List<ContestEntry> allParticipants;
	private List<VotingCountry> voters;
	private List<ContestEntry> finalParticipants;
	private List<CountryRating> countriesWithRating;
	private static final int numberOfParticipants = 46;
	private static final int year = 2015;


	public void setSimulationConfiguration(SimulationConfiguration simulationConfiguration) {
		Objects.requireNonNull(simulationConfiguration, "simulationConfiguration must not be null");
		this.simulationConfiguration = simulationConfiguration;
	}
	/**
	 * 
	 * @param inputJsonFileName - numele fisierului Json din care se citesc datele 
	 * @return o lista de cantece citita din fisierul Json
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Song> readSongsFromJson(String inputJsonFileName) throws JsonParseException, JsonMappingException, IOException {
		List<Song> songs = new ArrayList<Song>();
		ObjectMapper mapper = new ObjectMapper();
		songs = mapper.readValue(new File(inputJsonFileName),
				new TypeReference<List<Song>>() {
				});	
		return songs;
	}
	/**
	 * Citeste din fisierul csv tarile participante si le adauga in lista cu tarile care pot vota
	 * @throws IOException
	 */
	public void getListWithAllVotingCountries() throws IOException {
		this.countriesWithRating = new ArrayList<CountryRating>();
		this.voters = new ArrayList<VotingCountry>();
		int i = 0;
		Reader in = new FileReader(simulationConfiguration.getInputCsvFilename());
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("country","rating").parse(in);
		for (CSVRecord record :records) {
			  String country = record.get("country");
			  String rating = record.get("rating");
		      countriesWithRating.add(new CountryRating(country, Integer.parseInt(rating)));
		      voters.add(new VotingCountry(country));
		      i++;	      
		  }
	}
	/**
	 * initializeaza lista cu toate cele 46 de tari participante
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void getListWithAllParticipants() throws JsonParseException, JsonMappingException, IOException {
		 List<Song> songs = readSongsFromJson(simulationConfiguration.getInputJsonFilenames());
		 Collections.shuffle(songs);
		 getListWithAllVotingCountries();
		 List<Song> songList = new ArrayList<Song>();
		 songList = songs.subList(0,numberOfParticipants);
	     this.allParticipants = new ArrayList<ContestEntry>(20);
		 for(int j=0; j<songList.size(); j++) {
			 allParticipants.add(new ContestEntry(songList.get(j), countriesWithRating.get(j)));
		 }		
	}
	
	/**
	 * adauga la lista participantilor din finala tarile speciale care se califica direct
	 * @param specialCountries-lista de tari care merg direct in finala
	 */	
	public void addSpecialCountriesToFinal(List<String> specialCountries) {
		this.finalParticipants = new ArrayList<ContestEntry>();
		List<ContestEntry> copyOfAllParticipants = new ArrayList<ContestEntry>();
		copyOfAllParticipants.addAll(allParticipants);
		for(ContestEntry c: copyOfAllParticipants) {
			for(String s: specialCountries) {
				if(c.getCountryRating().getCountryName().equals(s)) {
					this.finalParticipants.add(c);
					allParticipants.remove(c);
				}				
			}
		}		
	}
	
	/**
	 * 
	 * @param participants- lista de participanti
	 */
	public void votingProcess(List<ContestEntry> participants) {
		for(VotingCountry v: voters){
			 v.setFinalScores(participants);
		 }		
	}
	
	/**
	 * scrie intr-un fisier CSV rezultatele
	 * @param fileName
	 * @param participants
	 */
	public void writeInCsvFile(String fileName, List<ContestEntry> participants) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		String NEW_LINE_SEPARATOR = "\n";
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		String[] FILE_HEADER ={"Country", "Score"};
		try{
			fileWriter = new FileWriter(fileName);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER[0],FILE_HEADER[1]);
			for(ContestEntry c: participants) {
				List eurovisionDataRecord = new ArrayList();
				eurovisionDataRecord.add(c.getCountryRating().getCountryName());
				eurovisionDataRecord.add(String.valueOf(c.getFinalScore()));
				csvFilePrinter.printRecord(eurovisionDataRecord);

			}
		} catch(Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush(); 
				fileWriter.close(); 
			    csvFilePrinter.close();
			  }catch (IOException e) {
				  System.out.println("error");
				 
			  }
		}	
	}
	
	public void sortListOfParticipants(List<ContestEntry> participants) {
		 Collections.sort(participants,new Comparator<ContestEntry>() {
				public int compare(ContestEntry c1, ContestEntry c2) {	
					return (int)(c2.getFinalScore()-c1.getFinalScore());
				}
			});		
	}

	/**
	 * This method (required by the Callable interface) should return the
	 * winning country. (which will organize next year the Eurovision contest)
	 */
	public String call() throws Exception {
		String inputCsvFilename = "eurovision-" + year + "-bets.csv";
		String inputJsonFileName="MediaProMusic.json";
		int numberOfSemifinalWinners = 10;
		String outputCsvFinal = "final-results.csv";
		String outputCsvSemifinal1 = "semifinal1-results.csv";
		String outputCsvSemifinal2 = "semifinal2-results.csv";
		List<String> specialCountries = new ArrayList<String>(6);
		specialCountries.add("Sweden");
		specialCountries.add("Russia");
		specialCountries.add("Italy");
		specialCountries.add("Norway");
		specialCountries.add("Estonia");
		specialCountries.add("Israel");
		
		SimulationConfiguration config = new SimulationConfiguration(inputCsvFilename,inputJsonFileName,
				specialCountries,numberOfSemifinalWinners,outputCsvSemifinal1,outputCsvSemifinal2,outputCsvFinal);
		this.setSimulationConfiguration(config);
		this.getListWithAllParticipants();
		this.addSpecialCountriesToFinal(specialCountries);
		Collections.shuffle(allParticipants);
		List<ContestEntry> semifinal1 = new ArrayList<ContestEntry>();
		List<ContestEntry> semifinal2 = new ArrayList<ContestEntry>();
		semifinal1 = allParticipants.subList(0, 20);
		semifinal2 = allParticipants.subList(20, allParticipants.size());
		votingProcess(semifinal1);
		votingProcess(semifinal2);
		sortListOfParticipants(semifinal1);
		writeInCsvFile(simulationConfiguration.getFilenameFirstSemifinal(),semifinal1);
		sortListOfParticipants(semifinal2);
		writeInCsvFile(simulationConfiguration.getFilenameSecondSemifinal(), semifinal2);
		List<ContestEntry> topTenFromSemifinal1 = semifinal1.subList(0,10);
		List<ContestEntry> topTenFromSemifinal2 = semifinal2.subList(0,10);
		finalParticipants.addAll(topTenFromSemifinal1);
		finalParticipants.addAll(topTenFromSemifinal2);
		for(ContestEntry e :finalParticipants) {
			e.setFinalScoreToZero();		
		}
		votingProcess(finalParticipants);
		sortListOfParticipants(finalParticipants);
		writeInCsvFile(simulationConfiguration.getFilenameFinal(),finalParticipants);
		ContestEntry winner = finalParticipants.get(0);
		return "The winner is " + winner.getCountryRating().getCountryName() + " with " + winner.getFinalScore() + " points.Congrats!";
	}

}
