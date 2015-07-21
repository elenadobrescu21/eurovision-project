package ro.esolutions.curs.eurovision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;




import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main2 {

	public static List<Song> readSongsFromJson(String inputJsonFileName) throws JsonParseException, JsonMappingException, IOException {
		List<Song> songs = new ArrayList<Song>();
		ObjectMapper mapper = new ObjectMapper();
		songs = mapper.readValue(new File(inputJsonFileName),
				new TypeReference<List<Song>>() {
				});
		
		return songs;
	}
	public static void main(String[] args) throws IOException {
	
		String inputJsonFileName="MediaProMusic.json";
		int year = 2015;
		int i = 0;
		String favorite = "Sweden";
		List<String> specialCountries = new ArrayList<String>(6);
		specialCountries.add("Sweden");
		specialCountries.add("Russia");
		specialCountries.add("Italy");
		specialCountries.add("Norway");
		specialCountries.add("Estonia");
		specialCountries.add("Israel");
		List<CountryRating> countriesWithRating = new ArrayList<CountryRating>();
		List<VotingCountry> voters = new ArrayList<VotingCountry>();
		String inputCsvFilename = "eurovision-" + year + "-bets.csv";
		 Reader in = new FileReader(inputCsvFilename);
		 Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader("country","rating").parse(in);
		  for (CSVRecord record :records) {
			  String country = record.get("country");
			  String rating = record.get("rating");
		      countriesWithRating.add(new CountryRating(country, Integer.parseInt(rating)));
		      voters.add(new VotingCountry(country));
		      i++;	      
		  }
		  
		/*  for(CountryRating c:countriesWithRating) {
			  System.out.println("Tara: " + c.getCountryName() + " Rating: " + c.getRating());
		  }
		  */
		  
		 List<Song> songs = readSongsFromJson(inputJsonFileName);
		/* System.out.println("Tarile din Json");
		 for(Song song:songs) {
			 System.out.println("Song name:" + song.getMetadata().getTitle() + " Artist:" + song.getMetadata().getArtist());
			 }
			 */
		 Collections.shuffle(songs);
		 List<Song> songList = new ArrayList<Song>();
		 songList = songs.subList(0, 20);
		 
		 List<ContestEntry> participants = new ArrayList<ContestEntry>(20);
		 for(int j=0; j<songList.size(); j++) {
			 participants.add(new ContestEntry(songList.get(j), countriesWithRating.get(j)));
		 }
		 
		/* for(ContestEntry e: participants) {
			System.out.println(e.getSong().getMetadata().getTitle() + " " + e.getCountryRating().getCountryName());
		 }
		 */
		 
		 for(VotingCountry v: voters){
			 v.getJuryVote(participants);
			 v.getPopularVote(participants);
			 v.setFinalScores(participants);
		 }
		 
		 Collections.sort(participants,new Comparator<ContestEntry>() {
				public int compare(ContestEntry c1, ContestEntry c2) {	
					return (int)(c2.getFinalScore()-c1.getFinalScore());
				}
			});
		 
		 System.out.println("Clasamentul final");
		 
		 for(ContestEntry e:participants){
			 System.out.println("Tara:" + e.getCountryRating().getCountryName() + " scor:" + e.getFinalScore());
		 }
		 
		//PrintWriter pw = null;
		/*
		try {
			pw = new PrintWriter(inputCsvFilename);
			//pw.println(String.format("COUNTRY,RATING"));
			
			pw.println(String.format("%s,3", favorite));
			pw.println(String.format("Ireland,5"));
			pw.println(String.format("Spain,9.5"));
			pw.println(String.format("Germany,7"));
			pw.println(String.format("Italy,18"));
			pw.println(String.forma("Russia"))
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
		*/
	}

}
