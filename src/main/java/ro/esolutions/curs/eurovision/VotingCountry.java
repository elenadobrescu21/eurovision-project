package ro.esolutions.curs.eurovision;

import java.util.*;

public class VotingCountry implements JuryVote, PopularVote {
	private String countryName;
	private boolean permissionToVote;
	private List<JuryMember> juriesList = new ArrayList<JuryMember>(5);
	private List<Integer> rankings;
	
	public VotingCountry(String countryName){
		this.countryName = countryName;	
	}
	
	public void createJuriesListArray(List<ContestEntry> participants) {
		for(int i=0; i<juriesList.size(); i++) {
			juriesList.add(new JuryMember(participants.size()));
		}
		for(JuryMember j: juriesList) {
			j.shuffleRankings();
		}
	}
	
	public void generatePointsArray(List<ContestEntry> participants) {
		this.rankings = new ArrayList<Integer>(participants.size());
		for(int i=0; i<participants.size(); i++) {
			this.rankings.add(i);
		}
	}
	
	public void getJuryVote(List<ContestEntry> participants) {
		
		this.createJuriesListArray(participants);
		for(int i=0; i<participants.size(); i++) {
			this.setPermissionToVote(participants.get(i));
			if(this.permissionToVote == true) {
				int sum = 0;
				for(JuryMember j : juriesList) {
					sum = sum + j.getRankings().get(i);
				}
				float media = sum/5;
				participants.get(i).setIntermediateJuryScore(media);	
			}
		}
		
		this.generatePointsArray(participants);
		Collections.sort(participants,new Comparator<ContestEntry>() {
			public int compare(ContestEntry c1, ContestEntry c2) {	
				double c;
				int rez = 0;
				double comparatie = 0.0;
				c = c1.getIntermediateJuryScore() - c2.getIntermediateJuryScore();
				if(c==comparatie) {
					rez = 0;
				}
				if(c<comparatie) {
					rez = -1;
				}
				if(c>comparatie) {
					rez = 1;
				}
				return rez;
				
			} 	
		});
		for(int i=0; i<participants.size(); i++) {
			participants.get(i).setIntermediateJuryScore(this.rankings.get(i));	
		}
		
	}
	
	public void getPopularVote(List<ContestEntry> participants) {
		
		for(ContestEntry e:participants) {
			e.setIntermediatePopularScore(rand.nextInt(1000));
		}
		
		Collections.sort(participants,new Comparator<ContestEntry>() {
			public int compare(ContestEntry c1, ContestEntry c2) {	
				double c;
				int rez = 0;
				double comparatie = 0.0;
				c = c1.getIntermediatePopularScore() - c2.getIntermediatePopularScore();
				if(c==comparatie) {
					rez = 0;
				}
				if(c<comparatie) {
					rez = -1;
				}
				if(c>comparatie) {
					rez = 1;
				}
				return rez;
			}
		});
		
		for(int i=0; i<participants.size(); i++) {
			participants.get(i).setIntermediatePopularScore(this.rankings.get(i));
		}			
	}
	
	public void setFinalScores(List<ContestEntry> participants) {
		
		for(ContestEntry e :participants) {
			float media = (e.getIntermediateJuryScore()+ e.getIntermediatePopularScore())/2;
			e.setAverageOfJuryAndTelevoting(media);
		}
		Collections.sort(participants,new Comparator<ContestEntry>() {
			public int compare(ContestEntry c1, ContestEntry c2) {	
				double c;
				int rez = 0;
				double comparatie = 0.0;
				c = c1.getAverageOfJuryAndTelevoting() - c2.getAverageOfJuryAndTelevoting();
				if(c==comparatie) {
					c = c1.getIntermediatePopularScore()-c2.getIntermediatePopularScore();
					if(c < comparatie) {
						rez = -1;
					}
					if(c > comparatie) {
						rez = 1;
					}
					
				}
				if(c < comparatie) {
					rez = -1;
				}
				if(c>comparatie) {
					rez = 1;
				}
				return rez;
			} 	
		});
		
		Collections.reverse(participants);
		for(int i=0; i<points.length; i++) {
			participants.get(i).addPointsToFinalScore(points[i]);
		}
		
		for(ContestEntry e:participants) {
			e.setIntermediateJuryScoreToZero();
			e.setIntermediatePopularScoreToZero();
		}		
	}
	
	public void setPermissionToVote(ContestEntry e) {
		if(this.countryName.equals(e.getCountryRating().getCountryName()) == true) {
			this.permissionToVote = false;
		}
		else {
			this.permissionToVote = true;
		}
		
	}
}
