
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {

    @Test
    public void myTest(){

      String  payload="{\n" +
              "  \"dashboard\": {\n" +
              "    \"purchaseAmount\": 910,\n" +
              "    \"website\": \"rahulshettyacademy.com\"\n" +
              "  },\n" +
              "  \"courses\": [\n" +
              "    {\n" +
              "      \"title\": \"Selenium Python\",\n" +
              "      \"price\": 50,\n" +
              "      \"copies\": 6\n" +
              "    },\n" +
              "    {\n" +
              "      \"title\": \"Cypress\",\n" +
              "      \"price\": 40,\n" +
              "      \"copies\": 4\n" +
              "    },\n" +
              "    {\n" +
              "      \"title\": \"RPA\",\n" +
              "      \"price\": 45,\n" +
              "      \"copies\": 10\n" +
              "    }\n" +
              "  ]\n" +
              "}";
        JsonPath jp= new JsonPath(payload);
        //print number of courses

        System.out.println(jp.getInt("courses.size()"));
        //print purchase amount
        System.out.println(jp.getInt("dashboard.purchaseAmount"));
        //print title of first course
        System.out.println(jp.get("courses[0].title"));
        //print all course titles and respective prices

        int ar_size=jp.getInt("courses.size()");
        for(int i=0;i<ar_size;i++){
            System.out.println(jp.get("courses["+i+"].title"));
            System.out.println(jp.getInt("courses["+i+"].price"));
        }
        //print number of copies sold by RPA course
        for(int i=0;i<ar_size;i++){
           String cor=(jp.get("courses["+i+"].title"));
           if (cor.equalsIgnoreCase("RPA")){
            System.out.println(jp.getInt("courses["+i+"].copies"));
            break;
           }
        }
        //verify sum of all course prices match with purchase amount
        int purch=jp.getInt("dashboard.purchaseAmount");
        int actual=0;
        for(int i=0;i<ar_size;i++){
            int cp=(jp.getInt("courses["+i+"].copies"))*(jp.getInt("courses["+i+"].price"));
            System.out.println("ind sum"+cp);
            actual= actual +cp;;
            }
//        if (purch==actual)
//            System.out.println("Equal");
//        else
//            System.out.println("Not Equal");
        Assert.assertEquals(purch,actual);
       }


    public static String GenerateStringFromResource(String path) throws IOException {


        return new String(Files.readAllBytes(Paths.get(path)));



    }


    }

