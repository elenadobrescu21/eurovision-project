package ro.esolutions.curs.eurovision;

public class CountryRating {
	private String countryName;
	private int rating;
	
	public CountryRating(String countryName, int rating) {
		this.countryName = countryName;
		this.rating = rating;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	

}
