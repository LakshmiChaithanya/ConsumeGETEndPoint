package consumeJsonDataFromREST.consumeJson.readService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
*Util class to read url content.
*Parse the json
*compute current sum (each iteration) and total sum (sum of all current sums)
*
* @author  Lakshmichaithanya Pernapati
* @since   5-14-2018
*/
@SuppressWarnings("unused")
public class ConsumeAndProcessData {

	public void parseAndDisplay(String restEndPointUrl) throws IOException {
		// The key name in GET response on which actual computation
		final String keyValue = "numbers";
	
	    // Connect to the URL using java's native library
	    URL url = new URL(restEndPointUrl);
	    URLConnection request = url.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); 
	    JsonElement root;
		try {
			//Convert the input stream to a json element
			root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			/*
			 * Please use this snippet of code if the input is in local 
			 * 
			 * */
			
			  //InputStream integerInputStream = ConsumeAndProcessData.class.getResourceAsStream("/sampleFile.json");
		     // BufferedReader br = new BufferedReader(new InputStreamReader(integerInputStream));
		     //root = new JsonParser().parse(br); // pass br if local file is being used
			
			// as input is flat json array get the content as json array			
			JsonArray jsonarray = root.getAsJsonArray();
			int totalSum = 0;
			// Iterate over json array elements
			for (int i = 0; i < jsonarray.size(); i++) {
			  
			  JsonObject record = (JsonObject) jsonarray.get(i);
			 
			  // get the data as array from the key: numbers in the record which is flat json object
			  JsonArray numArr = record.getAsJsonArray(keyValue);
			  // It is important that key is present or not
			  if(numArr != null){
			  int currentSum = 0;
				for (JsonElement numElem : numArr) {
					currentSum += numElem.getAsInt();
				}
				System.out.println("Current sum from numbers array : "+currentSum);
				totalSum += currentSum;
			  }
			  else
				  System.out.println("numbers key may be null or unavalable for this iteration");
			}
			System.out.println("total sum from flat numbers array json: "+totalSum);
		} catch (JsonIOException e) {
			System.out.println("Exception in parsing json file or input, make sure json is in required format"+e);
		} catch (JsonSyntaxException e) {
			System.out.println("Json is syntactically incorrect!!"+e);
		} catch (IOException e) {
			System.out.println("Exception in reading IO!"+e);
		}
	    
	}

}
