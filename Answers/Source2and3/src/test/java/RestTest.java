

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.*;

public class RestTest {
	private String baseUrl = "https://jsonplaceholder.typicode.com";
	int userId = getRandomId();

	@Test
	public void getRandomUserEmail() {
		//assume when they said by userId that means ID in the user table (no userID there).
		Response response = given().contentType("application/json").when().get(baseUrl + "/users?id={randomNumber}",
				getUserId());
		//with more time i would regex this email to check 
		Assert.assertTrue((response.jsonPath().get("email").toString().contains("@")), "Does not look like a vaild email");
	}

	@Test
	public void getPosts() {
		Response response = given().contentType("application/json").when().get(baseUrl + "/posts?userId={randomNumber}",
				getUserId());
		List<Integer> reponseList = new ArrayList <> ();
		reponseList = response.jsonPath().get("id");
		
		for (int id : reponseList ) {
			Assert.assertTrue(!(id < 1 && id > 100), "Post ID " + id + " is not valid");
		}
	}

	@Test
	public void createNewPost() throws Exception {
		Response response = given().contentType("application/json").body(jsonPutString(getRandomId())).when()
				.post(baseUrl + "/posts");
		Assert.assertEquals(response.statusCode() /*actual value*/, 201 /*expected value*/, "Incorrect status code returned");
	}

	public String jsonPutString(int userId) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("title", "bar");
		requestParams.put("body", "foo");
		requestParams.put("userId", userId);
		return requestParams.toJSONString();
	}

	public String jsonGetString(int id) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", id);
		return requestParams.toJSONString();
	}

	private int getUserId() {
		return userId;
	}
	
	public int getRandomId() {
		Random r = new Random();
		return (r.nextInt(10) + 1);
	}
}
