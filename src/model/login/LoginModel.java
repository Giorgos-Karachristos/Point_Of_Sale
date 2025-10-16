package model.login;

import org.json.JSONObject;
import util.HttpRequestUtil;

public class LoginModel implements LoginDAO{

    public String signIn(String username, String password) {
        JSONObject jsonInput = new JSONObject();
        jsonInput.put("username", username);
        jsonInput.put("password", password);

        return HttpRequestUtil.sendHttpRequest("login", "POST", jsonInput);
    }
}
