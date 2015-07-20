package ro.esolutions.curs.eurovision;

import java.util.List;

public interface PopularVote extends Vote{
	
	public void getPopularVote(List<ContestEntry> participants);

}
