package stockanalyzer.ctrl;

import stockanalyzer.ui.UserInterface;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.Result;
import yahooApi.beans.YahooResponse;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller {


	
	public void process(String ticker) {
		getData(ticker);
		System.out.println("Start process");

	}








		//TODO implement Error handling 

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse

	

	public QuoteResponse getData(String searchString){


		ArrayList<String> tickers = new ArrayList<>();
		tickers.add(searchString);
		YahooFinance yahooFinance = new YahooFinance();
		YahooResponse yahooResponse = yahooFinance.getCurrentData(tickers);
		QuoteResponse quotes = yahooResponse.getQuoteResponse();
		quotes.getResult().stream().forEach(q -> System.out.println("Aktie: " + q.getLongName() + "\nPreis: " +q.getAsk()));
		System.out.println("Max: "+ getHigh(quotes));
		System.out.println("Durschnittlicher Preis: "+ getAverage(quotes));
		System.out.println("Datensätze: "+ getDataCount(quotes));
		
		return quotes;
	}

	public double getHigh(QuoteResponse quoteResponse){
		double high = quoteResponse.getResult().stream().mapToDouble(q -> q.getRegularMarketDayHigh().doubleValue()).max().orElse(0.00);
		return high;
	}

	public double getAverage(QuoteResponse quoteResponse){
		double avg = quoteResponse.getResult().stream().mapToDouble(q ->q.getFiftyDayAverage().doubleValue()).average().orElse(0.00);
		return avg;
	}

	public int getDataCount(QuoteResponse quoteResponse){
		int counter = quoteResponse.getResult().size();
		return counter;
	}




	public void closeConnection() {
		
	}
}
