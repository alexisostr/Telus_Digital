package backend;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAPIChainTest {
	
	int bookingID = 0;
	String token;

	@Test(description = "Add new booking", priority = 0)
	public void postAPIRequestToGetBookingID() {
		// Read PostAPIPayload.json file
		File fileInput = new File(".\\src\\test\\resources\\PostAPIPayload.json");

		// Create Post Request
		Response resp = 
				given()
					.header("Content-type", "application/json")
					.and()
					.body(fileInput)
					.baseUri("https://restful-booker.herokuapp.com/booking")
				.when()
					.post()
				.then()
					//.log().all()
					.assertThat()
					.statusCode(200)
					.body("booking.firstname", Matchers.equalTo("Alex")) // Respond body assertions!!!
					.body("booking.totalprice", Matchers.equalTo(1100)) 
				.extract()
					.response();

		// Get BookingID
		bookingID = resp.jsonPath().getInt("bookingid");
		System.out.println(bookingID);
	}
	
	@Test(description = "Get Token", priority = 1)
	public void postAPIRequestToGetToken() {
		// Read tokenAPIPayload.json file
		File tokenfile = new File(".\\src\\test\\resources\\tokenAPIPayload.json");

		// Create Post Request
		Response resp =
				given()
				.header("Content-type", "application/json")
				.and()
				.body(tokenfile)
				.baseUri("https://restful-booker.herokuapp.com/auth")
			.when()
				.post()
			.then()
				//.log().all()
				.assertThat()
				.statusCode(200)
			.extract()
				.response();
		
		// Get token ID
		token = resp.jsonPath().getString("token");
		System.out.println(token);

	}
	
	@Test(description = "Update booking", priority = 2)
	public void putAPIRequestUsingToken() {
		
		// Read putAPIPayload.json file
		File putfile = new File(".\\src\\test\\resources\\PutAPIPayload.json"); 
		
		
				given()
					.contentType(ContentType.JSON)
					.auth()
					  .preemptive()
					  .basic("admin", "password123")
					.body(putfile)
					.header("Authorization", "Bearer " + token)
					.baseUri("https://restful-booker.herokuapp.com/booking")
				.when()
					.put("/{bookingid}",bookingID)
				.then()
					.log().body()
					.assertThat()
					.statusCode(200)
					.body("firstname", Matchers.equalTo("Java"))
					.body("lastname", Matchers.equalTo("RestAssured"));
				
	
	}

}
