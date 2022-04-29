package stockanalyzer.ctrl;

import stockanalyzer.ui.UserInterface;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
	
	public ArrayList<Object> process(String ticker) {
		ArrayList<Object> data = new ArrayList<>(getData(ticker));
		System.out.println("Start process");
		return data;
	}








		//TODO implement Error handling 

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse

	

	public ArrayList getData(String searchString) {


		ArrayList<String> tickers = new ArrayList<>();
		tickers.add(searchString);
		ArrayList<Object> tickersData = new ArrayList<>();
		YahooFinance yahooFinance = new YahooFinance();
		YahooResponse yahooResponse = yahooFinance.getCurrentData(tickers);
		QuoteResponse quotes = yahooResponse.getQuoteResponse();

		quotes.getResult().forEach(q -> tickersData.add(q.getLongName()));
		quotes.getResult().forEach(q -> tickersData.add(q.getAsk().toString()));
		
		return tickersData;
	}


	public void closeConnection() {
		
	}
}
