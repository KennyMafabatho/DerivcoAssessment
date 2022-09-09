import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Derivco {

    @BeforeTest
    public  void  setup(){
        baseURI = "https://www.thecocktaildb.com";
        filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test(dataProvider = "IngredientNameDataProvider")
    public void SearchIngredientName(String i){
            RequestSpecification httpsRequest = RestAssured.given();
            httpsRequest.contentType(ContentType.JSON);
            httpsRequest.param("i", i);
            Response response = httpsRequest.get(baseURI + "/api/json/v1/1/search.php");
            Assert.assertEquals(response.statusCode(), 200);
        }
    @Test(dataProvider = "CocktailNameDataProvider")
    public void SearchCocktailByName(String s)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("s", s);
        Response response = httpRequest.get(baseURI + "/api/json/v1/1/search.php");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "InvalidCocktailNameDataProvider")
    public void SearchInvalidCocktailName(String s)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("s", s);
        Response response = httpRequest.get(baseURI + "/api/json/v1/1/search.php");
        Assert.assertEquals(response.statusCode(), 200);
    }
    @Test(dataProvider = "DeleteCocktailDataProvider")
    public void VerifyDeleteCocktail(String s)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("s", s);
        Response response = httpRequest.delete(baseURI + "/api/json/v1/1/search.php");
        Assert.assertEquals(response.statusCode(), 405);
    }
    @Test(dataProvider = "SearchCocktailByIdDataProvider")
    public void SearchCocktailNameById(String i)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("i", i);
        Response response = httpRequest.get(baseURI + "/api/json/v1/1/lookup.php");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "SearchIngredientByIdDataProvider")
    public void SearchIngredientById(String iid)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("iid", iid);
        Response response = httpRequest.get(baseURI + "/api/json/v1/1/lookup.php");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "ListCocktailsByFirstLetterDataProvider")
    public void ListCocktailsByFirstLetter(String f)
    {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.param("f", f);
        Response response = httpRequest.get(baseURI + "/api/json/v1/1/search.php");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @DataProvider(name = "IngredientNameDataProvider")
    public Object [] [] IngredientNameDataProvider(){
        return  new Object [][] {{"vodka"}, {"VODKA"}};
    }
    @DataProvider(name = "CocktailNameDataProvider")
    public Object[][] CocktailNameDataProvider(){
        return new Object[][] {{"margarita"}, {"MARGARITA"}};
    }
    @DataProvider(name = "InvalidCocktailNameDataProvider")
    public Object [] [] InvalidCocktailNameDataProvider(){
        return  new Object [][] {{"Cheese"}};
    }
    @DataProvider(name = "DeleteCocktailDataProvider")
    public Object [] [] DeleteCocktailDataProvider(){
        return  new Object [][] {{"Margarita"}};
    }
    @DataProvider(name = "SearchCocktailByIdDataProvider")
    public Object [] [] SearchCocktailByIdDataProvider(){
        return  new Object [][] {{"11007"}};
    }
    @DataProvider(name = "SearchIngredientByIdDataProvider")
    public Object [] [] SearchIngredientById(){
        return  new Object [][] {{"552"}};
    }
    @DataProvider(name = "ListCocktailsByFirstLetterDataProvider")
    public Object [] [] ListCocktailsByFirstLetterDataProvider(){
        return  new Object [][] {{"C"}};
    }

}
