package UserData;

import api.TokenModel;
import api.Utils;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class UserGenerator {
    static Faker faker = new Faker();

    public static String name = faker.name().name();
    public static String email = faker.internet().emailAddress();
    public static String password = faker.internet().password();
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private static final String REGISTER_PATH = "api/auth/register";
    private static final String LOGIN_PATH = "api/auth/login";

    public UserGenerator() {
        RestAssured.baseURI = BASE_URI;
    }

    public ValidatableResponse create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER_PATH)
                .then();
    }

    public ValidatableResponse login(User creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    public ValidatableResponse delete(User user, TokenModel tokenResponse) {
        return given()
                .auth().oauth2(Utils.getTokenWithoutBearer(tokenResponse.getAccessToken()))
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
    }
}
