import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OauthTest {

    public static void main(String args[]){

      //  System.setProperty("webdriver.chrome.driver","/Users/nimithas/Desktop/chromedriver.exe");
      //  WebDriver dv=new ChromeDriver();
     //   dv.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

// TODO Auto-generated method stub

        String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";



        String access_tokenresp=  given()
                .queryParam("code","")
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("grant_type","authorization_code")
                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        JsonPath jp=new JsonPath(access_tokenresp);
       String accesstok=jp.getString("access_token");

     String response =   given().queryParam("access_token",accesstok)
                .when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println("response >>>>"+response);


    }
}
