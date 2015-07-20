package ro.esolutions.curs.eurovision;

import java.util.List;

public interface JuryVote extends Vote {
	
	public void getJuryVote(List<ContestEntry> participants);

}
