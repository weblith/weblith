package io.weblith.core.scopes;

import io.quarkus.test.QuarkusUnitTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import test.controllers.RequestContextAwareController;

import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class RequestContextAwareControllerTest {

    private final static int OK = Status.OK.getStatusCode();

    @RegisterExtension
    static QuarkusUnitTest runner = new QuarkusUnitTest()
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
                    .addClasses(RequestContextAwareController.class)
                    .addAsResource(new StringAsset("quarkus.http.test-port=0"), "application.properties"));

    @Test
    public void testAuthenticityTokenExist() {
        // Will also checks the session exist
        when().get("/Ctx/authenticityToken")
                .then().statusCode(OK).body(not(emptyOrNullString()));
    }

}