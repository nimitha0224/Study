import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Basics {

    public static void main(String  args[]) throws IOException {
//validate ADDplace API
        //Given - All input details
        //when - Submit the API : resource and http method  goes here
        //Then - Validate the response
        RestAssured.baseURI ="https://rahulshettyacademy.com";
        String newAddress="677 rihjjk way";

       String response= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Util.GenerateStringFromResource("/Users/nimithas/IdeaProjects/RAssured/src/main/resources/payload.json"))
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("server",equalTo("Apache/2.4.18 (Ubuntu)"))
                .extract().response().asString()
               ;
       JsonPath js= new JsonPath(response);
       String placeId=js.getString("place_id");
        System.out.println("placeId:: "+placeId);

        //update the address
        given().log().all()
                .queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"place_id\":\""+placeId+"\",\n" +
                        "    \"address\":\"677 rihjjk way\",\n" +
                        "    \"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"))
                ;

      String respAddress=  given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
               .extract().response().asString()
                ;

      JsonPath jp1 =new JsonPath(respAddress);
      String actualAddress=jp1.getString("address");
     Assert.assertEquals(newAddress,"ppppp");
    }
}
