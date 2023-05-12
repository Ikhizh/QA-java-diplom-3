package api;

import userdata.UserCreds;

import static config.AppConfig.LOGIN_PATH_API;
import static io.restassured.RestAssured.given;


public class Request {
    public static TokenModel requestUserToken(UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .body(userCreds)
                .post(LOGIN_PATH_API)
                .body().as(TokenModel.class);
    }
}
