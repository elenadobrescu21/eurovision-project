package ro.esolutions.curs.eurovision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuryMember {
	
	private List<Integer> rankings;
	
	public JuryMember(int numberOfElements) {
		this.rankings = new ArrayList<Integer>(numberOfElements);
		for(int i=1; i<=numberOfElements; i++) {
			rankings.add(i);
		}
	}
	
	public void shuffleRankings() {
		Collections.shuffle(this.rankings);
	}
	
	public List<Integer> getRankings() {
		return this.rankings;
	}
}
