package ro.esolutions.curs.eurovision;

public class ContestEntry {
	private Song song;
	private CountryRating countryRating;
	private int finalScore;
	private float intermediateJuryScore;
	private float intermediatePopularScore;
	private float averageOfJuryAndTelevoting;
	
	public ContestEntry(Song song, CountryRating countryRating) {
		this.song = song;
		this.countryRating = countryRating;
		this.finalScore = 0;
		this.intermediateJuryScore = 0;
		this.intermediatePopularScore = 0;
		
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public CountryRating getCountryRating() {
		return countryRating;
	}

	public void setCountryRating(CountryRating countryRating) {
		this.countryRating = countryRating;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public float getIntermediateJuryScore() {
		return intermediateJuryScore;
	}

	public void setIntermediateJuryScore(float intermediateJuryScore) {
		this.intermediateJuryScore = intermediateJuryScore;
	}

	public float getIntermediatePopularScore() {
		return intermediatePopularScore;
	}

	public void setIntermediatePopularScore(float intermediatePopularScore) {
		this.intermediatePopularScore = intermediatePopularScore;
	}

	public float getAverageOfJuryAndTelevoting() {
		return averageOfJuryAndTelevoting;
	}

	public void setAverageOfJuryAndTelevoting(float averageOfJuryAndTelevoting) {
		this.averageOfJuryAndTelevoting = averageOfJuryAndTelevoting;
	}
	
	public void setIntermediateJuryScoreToZero() {
		this.intermediateJuryScore = 0;
	}
	
	public void setIntermediatePopularScoreToZero() {
		this.intermediatePopularScore = 0;
	}
	
	public void addPointsToFinalScore(int points) {
		this.finalScore = this.finalScore + points;
	}

}
