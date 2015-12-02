package lee.gs_tracker.Diablo;

/**
 * Created by Matt on 11/8/2015.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import lee.gs_tracker.APIConnection;

public class DiabloAPIUser {

    public static String btag;
    public static JSONObject Obj, charObj;

    public static void UseAPI() throws Exception{
        String InputURL = "https://us.api.battle.net/d3/profile/" + btag + "/?locale=en_US&apikey=dzdyu73w47us458g5h89grjqgfs4ctpw";
        Obj = APIConnection.sentGet(InputURL);//should work even though it's in the WOWAPI class

    }

    public static JSONArray getCharArray()throws Exception {
        JSONArray charArray = Obj.getJSONArray("heroes");
        return charArray;
    }

    public static JSONArray getActiveSkillArray() throws Exception{
        JSONObject skillArray = charObj.getJSONObject("skills");
        JSONArray active = skillArray.getJSONArray("active");
        return active;
    }

    public static JSONArray getPassiveSkillArray() throws Exception{
        JSONObject skillArray = charObj.getJSONObject("skills");
        JSONArray active = skillArray.getJSONArray("passive");
        return active;
    }

    public static void UseCharAPI(int i) throws Exception{
        JSONArray charArray = DiabloAPIUser.getCharArray();
        JSONObject character = charArray.getJSONObject(i);
        String sid = character.get("id").toString();
        int id = Integer.parseInt(sid);
        String InputURL = "https://us.api.battle.net/d3/profile/" + btag + "/hero/" + id + "?locale=en_US&apikey=dzdyu73w47us458g5h89grjqgfs4ctpw";
        charObj = APIConnection.sentGet(InputURL);
    }



}
