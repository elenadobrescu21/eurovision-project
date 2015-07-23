package ro.esolutions.curs.eurovision;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimulationConfiguration {

	private String inputCsvFilename;

	private String inputJsonFilenames;

	private List<String> specialCountries;

	private int numberOfSemifinalWinners;

	private String filenameFinal;

	private String filenameFirstSemifinal;

	private String filenameSecondSemifinal;

	public SimulationConfiguration(String inputCsvFilename, String inputJsonFilename,
			List<String> specialCountries, int numberOfSemifinalWinners, String filenameFirstSemifinal, String filenameSecondSemifinal,
			String filenameFinal) {
		super();
		this.inputCsvFilename = inputCsvFilename;
		this.inputJsonFilenames = inputJsonFilename;
		this.specialCountries = specialCountries;
		this.numberOfSemifinalWinners = numberOfSemifinalWinners;
		this.filenameFirstSemifinal = filenameFirstSemifinal;
		this.filenameSecondSemifinal = filenameSecondSemifinal;
		this.filenameFinal = filenameFinal;
	}

	/**
	 * @return lista de tari speciale, care intra direct in finala 
	 * de ex. 5 mari finantatori + 1 organizator/gazda
	 */
	public List<String> getSpecialCountries() {
		return specialCountries;
	}

	/**
	 * @return CSV filename for the Final's results
	 */
	public String getFilenameFinal() {
		return filenameFinal;
	}
	

	/**
	 * @return CSV filename for the First Semifinal's results
	 */
	public String getFilenameFirstSemifinal() {
		return filenameFirstSemifinal;
	}

	/**
	 * @return CSV filename for the Second Semifinal's results
	 */
	
	public String getFilenameSecondSemifinal() {
		return filenameSecondSemifinal;
	}
	

	/**
	 * @return CSV filename 
	 * having 2 columns: COUNTRY, RATING 
	 * (given by a bet agency)
	 */

	public String getInputCsvFilename() {
		return inputCsvFilename;
	}

	/**
	 * @return a list of filenames (JSON files containing artists / titles / durations)
	 */

	public String getInputJsonFilenames() {
		return inputJsonFilenames;
	}

	/**
	 * @return the number of winners from _each_ of the 2 semifinals
	 */
	public int getNumberOfSemifinalWinners() {
		return numberOfSemifinalWinners;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
