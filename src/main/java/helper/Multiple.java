package helper;
import java.io.IOException;

import org.json.JSONObject;

public class Multiple extends Thread{
    public Summoner s;
    JSONObject obj;
    
	public Multiple(Summoner s) {
		this.s = s;
		obj =  new JSONObject();
	}
	@Override
	public void run() {
		try {
			System.out.println(s.getGameName()+"Point 1");
			int magic = Commons.processPlayer(s);
			if(magic == 0) {
	        	obj.put("errorMessage","is an invalid player name, please retry" );
	        	obj.put("playerName", s.getGameName().replace("%20", " "));
			} else if(magic == 1){
	        	obj.put("errorMessage","hasn't play more than 20 ranks in recent months, not able to show his datas" );
	        	obj.put("playerName", s.getGameName().replace("%20", " "));
			} else {
			    System.out.println(s.getGameName()+"Point 2");
                obj.put("playerName",s.getGameName().replace("%20", " "));
                obj.put("rank",s.getRank());
                obj.put("recentWrKda",s.getRecentWrKda());
                obj.put("overAllWr",s.getOverAllWr());
                obj.put("win/loss",s.getWinsTotal()+"/"+s.getLossTotal());
                obj.put("dodgeWarning",s.checkDodgeWarning());
                obj.put("PreferPositions",s.getPreferPositions());
                System.out.println(s.getPreferPositions());
                obj.put("errorMessage","");
			}
		} catch (IOException e) {

        	System.out.println(s.getGameName()+"Point Error: " + e.toString());
		}
	}
	
	public JSONObject getJsonObj() {
		System.out.println(s.getGameName()+"Point 3");
		return obj;
	}
	
	public Summoner getSummoner(){
		return s;
	}
}