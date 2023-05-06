package api;

import UserData.UserCreds;

import static io.restassured.RestAssured.given;


public class Request {
    public static TokenModel requestUserToken(UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .body(userCreds)
                .post("api/auth/login")
                .body().as(TokenModel.class);
    }
}
