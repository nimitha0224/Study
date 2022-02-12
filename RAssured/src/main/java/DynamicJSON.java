import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJSON {

    @Test(dataProvider ="Bookdata" )
    public void AddBook(String isbn,String aile){

        RestAssured.baseURI= "http://216.10.245.166";

    String resp=given().log().all().header("Content-Type","application/json")
        .body(payload(isbn,aile))
            .when().post("/Library/Addbook.php")
            .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath jp= new JsonPath(resp);
       String id= jp.get("ID");



    }

    @DataProvider(name="Bookdata")
    public Object[][] getData(){
        return new Object[][]{{"aaaa","12344"},{"dddda","333331"},{"ffgfff","333341"},{"gghhgg","544441"}};
    }
    public String payload(String is,String ail){
        return "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+is+"\",\n" +
                "\"aisle\":\""+ail+"\",\n" +
                "\"author\":\"John foer\"\n" +
                "}";
    }
}
