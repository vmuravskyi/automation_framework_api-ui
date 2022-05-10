package com.rest.graphQL;

import static io.restassured.RestAssured.given;

import com.rest.graphQL.dto.GraphQLQuery;
import com.rest.graphQL.dto.QueryVariable;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GraphQLQueryTest {

    private final String TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyNzk1M2IwYzMyMTkyMDA2ODQxNzUwMSJ9LCJuaWNrbmFtZSI6InYubXVyYXZza3lpIiwibmFtZSI6InYubXVyYXZza3lpQGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci80Yjc5NjZjYTg5YTZiNWQyMjdhNTdkNmNmYjJiNzgwYj9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRnYucG5nIiwidXBkYXRlZF9hdCI6IjIwMjItMDUtMDlUMTc6NDc6MjkuNTQwWiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDYyNzk1M2IwYzMyMTkyMDA2ODQxNzUwMSIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNjUyMTc0ODQzLCJleHAiOjE2NTIyMTA4NDMsImF0X2hhc2giOiI3c04xdVAzeVVCc0g2RjVGWDNkRHRRIiwibm9uY2UiOiI2fk42VkVtMTFzeFNGWTJuVHpwaGJ4YWtCWExiN09YNyJ9.JHsCFloz0T7xQTdQowGVPPwaHksS8odmCGpdOmz_6f5YjmNeyu8Yz8SJp14HRqcCRTGRSrLp2Gn8_mcxVuYiSZLoqCCv5uSYjfnKhfe1LJUNAME5euBH6qcN7JXZWAKh7nX7gXpeIJN00SUVG2xdF3w_5wru7NnJNnvABbmiLVWtAwUOuoUIWgflGl1lvSxafjDiW2c1hqKpAqHcEaENwTpLfqwgLS5VD61EAxxlb_GqYTx8OsW2MumDIhNkhuBOyLyVOp7psMTLCvtgva11XmDF7w_COY44pG8DxxSpejI0WZwgJ4bfg4Dklyw4zgDkhCE3agYVMiCCpHQN-yJJzQ";
    private final String BASE_URI = "https://swapi-graphql.netlify.app/.netlify/functions/index";

    @Test
    public void getAllFilms() {

        String baseUri = "https://swapi-graphql.netlify.app/.netlify/functions/index";
        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n    }\\n  }\\n}\",\"variables\":null}";

        given().baseUri(baseUri)
            .contentType(ContentType.JSON)
            .body(query)
            .when()
            .log().all()
            .post()
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
    }

    @DataProvider(name = "getQueryData")
    private Object[][] data() {
        return new Object[][]{
            {"10", "akshayapsangi123", "Flutter development"}
        };
    }

    @Test(dataProvider = "getQueryData")
    public void getAllUsers(String limit, String name, String titleName) {

        String query = String.format(
            "{\"query\":\"{\\n  users(limit: %s, where: {name: {_eq: \\\"%s\\\"}}) {\\n    id\\n    name\\n    todos(where: {title: {_eq: \\\"%s\\\"}}) {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}",
            limit, name, titleName);
        given().baseUri("https://hasura.io")
            .contentType(ContentType.JSON)
            .headers("Content-Type", ContentType.JSON)
            .headers("Authorization", TOKEN)
            .body(query)
            .log().all()
            .when()
            .post("/learn/graphql")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .extract()
            .response();
    }

    @Test
    public void getLimitUsersUsingDto() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query($limit:Int!) {\n"
            + "  users(limit: $limit) {\n"
            + "    id\n"
            + "    name\n"
            + "  }\n"
            + "}\n");
        QueryVariable queryVariable = new QueryVariable().setLimit(2);
        query.setVariables(queryVariable);

        given().baseUri("https://hasura.io")
            .contentType(ContentType.JSON)
            .headers("Content-Type", ContentType.JSON)
            .headers("Authorization", TOKEN)
            .body(query)
            .log().all()
            .when()
            .post("/learn/graphql")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .and()
            .body("data.users[0].name", Matchers.equalTo("tui.glen"),
                "data.users[1].name", Matchers.equalTo("dassad"));
    }

    @Test
    public void getUserWithTwoParameters() {

        GraphQLQuery query = new GraphQLQuery();
        query.setQuery("query ($limit: Int!, $name:String!) {\n"
            + "  users(limit: $limit, where: {name: {_eq: $name}}) {\n"
            + "    id\n"
            + "    name\n"
            + "  }\n"
            + "}\n");

        QueryVariable queryVariable = new QueryVariable()
            .setLimit(2)
            .setName("tui.glen");

        query.setVariables(queryVariable);

        given().baseUri("https://hasura.io")
            .contentType(ContentType.JSON)
            .headers("Content-Type", ContentType.JSON)
            .headers("Authorization", TOKEN)
            .body(query)
            .log().all()
            .when()
            .post("/learn/graphql")
            .then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .and()
            .body("data.users[0].name", Matchers.equalTo("tui.glen"));
    }

}
