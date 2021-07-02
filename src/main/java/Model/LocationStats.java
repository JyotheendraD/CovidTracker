package Model;

public class LocationStats {
	
	private String country;
	private String state;
	private int latestReports;
	private int difference;

	public int getDifference() {
		return difference;
	}

	public void setDifference(int difference) {
		this.difference = difference;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getLatestReports() {
		return latestReports;
	}

	public void setLatestReports(int i) {
		this.latestReports = i;
	}

	@Override
	public String toString() {
		return "LocationStats [country=" + country + ", state=" + state + ", latestReports=" + latestReports + "]";
		
	}

	

}
