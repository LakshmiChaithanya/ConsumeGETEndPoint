package consumeJsonDataFromREST.consumeJson.consumeGET;

import java.io.IOException;

import consumeJsonDataFromREST.consumeJson.readService.ConsumeAndProcessData;

/**
*simple class using native java that will accept a url as an 
*input(restEndPointUrl) argument and consume HTTP GETand process the output.
*
* @author  Lakshmichaithanya Pernapati
* @since   5-14-2018
*/

public class GetRESTEndPointAndConsume {

	public static void main(String[] arguments) {
		if(arguments != null && arguments.length == 1){
			//restEndPointUrl is the url which emits a json data
			String restEndPointUrl = arguments[0];
			// util class to solve business case
			ConsumeAndProcessData consumeAndProcessData = new ConsumeAndProcessData();
			try {
				consumeAndProcessData.parseAndDisplay(restEndPointUrl);
			} catch (IOException e) {
				System.out.println("Exception in reading url!"+e);
			}
		}
		else{
			System.out.println("Please make sure to mention the REST-GET endpoint resource from command line argument!!");
		}
	}

}
