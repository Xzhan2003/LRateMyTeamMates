package remoteprocedurecall;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.*;
import helper.Commons;
import helper.Multiple;
import helper.Summoner;

/**
 * Servlet implementation class SearchTeammate
 */
public class SearchTeammate extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_USERNAME_COUNT = 5;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTeammate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("finally")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		BufferedReader reader = new BufferedReader(request.getReader());
		StringBuilder requestBody = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null) {
			requestBody.append(line);
		}
		//System.out.println(requestBody.toString());
		JSONArray usernames = new JSONArray(requestBody.toString());
		//System.out.println(usernames);
		ArrayList<String> playerList = new ArrayList<>();
	    for (int i = 0; i < usernames.length(); i++) {
	      JSONObject objectInArray = usernames.getJSONObject(i);
	      String[] elementNames = JSONObject.getNames(objectInArray);
	      for(String s: elementNames) {
	    	  String value = objectInArray.getString(s);
	    	  playerList.add(value);
	        }
	    }
	    System.out.println(playerList);
		//////////////////////////////////////////		       
        long start1 = System.currentTimeMillis();
        ArrayList<Multiple> tempArr = new ArrayList<>();
        JSONArray jarray = new JSONArray();
        for(String playerName: playerList){
        	try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	String playerNameFix = playerName.replace(" ", "%20");
            Summoner summoner1 = new Summoner(playerNameFix);
            Multiple m = new Multiple(summoner1);
            m.start();
            tempArr.add(m);
        }
        try {
			Thread.sleep(8500);  // It decides the speed of the program
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        for(Multiple m:tempArr) {
        	jarray.put(m.getJsonObj());
        }

        long end1 = System.currentTimeMillis();
        double TotalTime = (end1-start1)/1000.0;
        String Times = "Time used: "+ TotalTime +" seconds";
        //System.out.println(jarray);
        writer.print(jarray);
        System.out.println(Times);
        //writer.print("Time used: "+ TotalTime +" seconds");
	}
}
