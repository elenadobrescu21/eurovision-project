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
	
	public boolean getPermissionToVote() {
		return this.permissionToVote;
	}
	
	public void generatePointsArray(List<ContestEntry> participants) {
		this.rankings = new ArrayList<Integer>(participants.size());
		for(int i=1; i<=participants.size(); i++) {
			this.rankings.add(i);
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
	
	/**
	 * Votul juriului
	 * Fiecare membru al juriului va avea un clasament:va acorda un numar maxim de puncte(egal cu numarul total de participanti
	 * din acea semifinala, respectiv finala) melodiei preferate, apoi va nota toate celelalte melodiile in ordinea preferintelor.
	 * Pentru fiecare tara se face media punctajului obtinut de la toti membrii juriului
	 */
	
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
	
	/**
	 * Fiecare tara primeste un anumit numar de voturi de la public
	 * In functie de acest numar de voturi se sorteaza lista crescator
	 * Dupa ordonare, i se atribuie fiecarei tari un anumit numar de puncte(de la 1 la numarul total de tari participante)
	 * Tara cu cele mai putine voturi va primi 1 punct si tara cu cele mai multe voturi va primi un punctaj egal cu numarul total
	 * de participanti din semifinala, respectiv finala
	 */
	public void getPopularVote(List<ContestEntry> participants) {	
		for(int i=0; i<participants.size(); i++) {
			this.setPermissionToVote(participants.get(i));
			if(this.permissionToVote == true) {
			participants.get(i).setIntermediatePopularScore(rand.nextInt(1000));
			}
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
	
	/**
	 * Se face media finala intre votul juriului si votul publicului
	 * 
	 */
	public void setFinalScores(List<ContestEntry> participants) {		
		this.getJuryVote(participants);
		this.getPopularVote(participants);		
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
}
