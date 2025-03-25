package it.softengunina.dietilogin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * Cognito has a custom logout url.
 * See more information <a href="https://docs.aws.amazon.com/cognito/latest/developerguide/logout-endpoint.html">here</a>.
 */
public class CognitoLogoutHandler extends SimpleUrlLogoutSuccessHandler {

    /**
     * The domain of your user pool.
     */
    private static final String DOMAIN = "https://<user pool domain>";

    /**
     * An allowed callback URL.
     */
    private static final String LOGOUT_REDIRECT_URL = "<logout uri>";

    /**
     * The ID of your User Pool Client.
     */
    private static final String USER_POOL_CLIENT_ID = "5r9dagi5svagti5v9999ar9rs5";

    /**
     * Here, we must implement the new logout URL request. We define what URL to send our request to, and set out client_id and logout_uri parameters.
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        return UriComponentsBuilder
                .fromUri(URI.create(DOMAIN + "/logout"))
                .queryParam("client_id", USER_POOL_CLIENT_ID)
                .queryParam("logout_uri", LOGOUT_REDIRECT_URL)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}
