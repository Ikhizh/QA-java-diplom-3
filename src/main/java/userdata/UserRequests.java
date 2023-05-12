package userdata;

import api.TokenModel;
import api.Utils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static config.AppConfig.*;
import static io.restassured.RestAssured.given;


public class UserRequests {

    public UserRequests() {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Register user")
    public ValidatableResponse create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER_PATH_API)
                .then();
    }

    @Step("Login user")
    public ValidatableResponse login(UserCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH_API)
                .then();
    }

    @Step("Delete user")
    public ValidatableResponse delete(User user, TokenModel tokenResponse) {
        return given()
                .auth().oauth2(Utils.getTokenWithoutBearer(tokenResponse.getAccessToken()))
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .delete(USER_URL)
                .then()
                .statusCode(202);
    }

}
