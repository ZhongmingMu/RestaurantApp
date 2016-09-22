package com.laioffer.laiofferproject;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.scribe.model.Token;
/**
 * Created by zhongmingmu on 9/6/16.
 */
public class YelpApi {

    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search";

    private static final String CONSUMER_KEY = "8YeiBgXvTEMIWHHBz9KwpQ";
    private static final String CONSUMER_SECRET = "qLgCWpkRvPNZmi2ibfnPUEJrQzk";
    private static final String TOKEN = "_AhdTGkl1nSd9FLyg45uvhnpoqII8_9f";
    private static final String TOKEN_SECRET = "fXVVRPk4qMQWxC6f3O2lq3NJutg";

    private OAuthService service;
    private Token accessToken;

    /**
     * Setup the Yelp API OAuth credentials.
     */
    public YelpApi() {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(CONSUMER_KEY)
                .apiSecret(CONSUMER_SECRET).build();
        this.accessToken = new Token(TOKEN, TOKEN_SECRET);
    }

    /**
     * Fire a search request to Yelp API.
     */
    public String searchForBusinessesByLocation(String term, String location, int searchLimit) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "http://" + API_HOST + SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(searchLimit));
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

}
