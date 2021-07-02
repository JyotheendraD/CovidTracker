package Services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Model.LocationStats;
import java.util.*;

@Service
public class CovidDataPuller {
	
	private static  String Data_Fetcher = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> stats = new ArrayList<>();
	public List<LocationStats> getStats() {
		return stats;
		}
	@PostConstruct
	@Scheduled(cron = "* * * * * *")
	public void puller() throws IOException, InterruptedException
	{
	    List<LocationStats> newStats = new ArrayList<>();
		HttpClient Puller = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(Data_Fetcher))
				.build();
		
		HttpResponse<String> httpResponse = Puller.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader = new  StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
		    LocationStats locationStat = new LocationStats();
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			int latestCases = Integer.parseInt(record.get(record.size()-1));
			int prev = Integer.parseInt(record.get(record.size()-2));
			locationStat.setLatestReports(latestCases);
			locationStat.setDifference(latestCases - prev);
			newStats.add(locationStat); 
		}
		this.stats = newStats;
		
		
		
	}
	


}
