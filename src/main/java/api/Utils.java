package api;

public class Utils {

    public static String getTokenWithoutBearer(String token){
        return token.split("Bearer ")[1];
    }
}
