package parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DatatypeConverter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SportmasterParser {
	
	public SportmasterParser (){}
	
	protected String defaultUrl = "http://www.sportmaster.ru/catalog/product/search.do?text=";
	protected String sortedUrl = "http://www.sportmaster.ru/catalog/product/search.do?sortOrder=ASC&sortBy=price&text=";
	protected String url = sortedUrl;
	protected String className = "sm-category__item";
	protected String dataClass = "js-compare-link";
	protected String tagCount = "h1";
	
	protected String name = "data-name";
	protected String id = "data-id";
	protected String price = "data-price";
	protected String img = "src";
	
	protected Map<String, String> map;

	private int queryCount = -1;
	
	/**
	 * @param sorted change query process:
	 * 		  if true, the result from the cheapest 
	 * 	      to the most expensive and the most popular otherwise
	 */
	public void setSorted(boolean sorted){
		if (sorted)
			url = sortedUrl;
		else url = defaultUrl;
	}
	private final int getCount(String tag, Document doc){
		return getCount(doc.getElementsByTag(tag).first());
	}
	private final int getCount(Element element){
		return getCount(element.text());
	}

	private final int getCount(String text){
		return Integer.parseInt(text.replaceAll("\\D", ""));
		
	}
	
	/**
	 * @return item result count of last query 
	 * 		   or -1 if it was not the last request 
	 */
	public final int lastCount(){
		return queryCount;
	}
	
	/**
	 * @param itemName which 'll matched <a href="sportmaster.ru">here</a>
	 * @return list with query result
	 * @throws MalformedURLException - if the request URL is not a HTTP 
	 * 		        or HTTPS URL, or is otherwise malformed 
	 *  	   HttpStatusException - if the response is not OK 
	 *  	        and HTTP response errors are not ignored 		              
	 *		   UnsupportedMimeTypeException - if the response mime type is not supported 
	 *		        and those errors are not ignored 						  
     *         SocketTimeoutException - if the connection times out 
 	 *		   IOException - on error 
	 */
	public final List<Product> query(String itemName) throws IOException{
		String search = Util.asHtmlHex(itemName);
		Document doc = Jsoup.connect(url + search).get();
		queryCount = getCount(tagCount, doc);
		if (queryCount <= 0)
			return Collections.emptyList();
		Elements elements = doc.getElementsByClass(className);
		List<Product> list = new ArrayList<>();
		for (Element e: elements){
			Element data = e.getElementsByClass(dataClass).first();
			Product product = new Product();
			product.setImgSource(e.getElementsByAttribute(img).first().attr(img));
			product.setName(data.attr(name));
			product.setPrice(data.attr(price));
			product.setProductID(data.attr(id));
			list.add(product);
		}
		return list;
	}
}
