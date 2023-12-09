package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private static final int SUCCESSFUL_RESPONSE = 200;
    private static final Logger LOGGER = Logger.getLogger(HackerNews.class.getName());
    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String ITEM_URL_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    private HackerNews() {
    }

    public static long[] hackerNewsTopStories() {
        try {
            String response = sendHttpRequest(TOP_STORIES_URL);

            if (response != null) {
                String[] idStrings = response.replaceAll("[\\[\\]\"]", "").split(",");

                long[] ids = new long[idStrings.length];

                for (int i = 0; i < idStrings.length; i++) {
                    ids[i] = Long.parseLong(idStrings[i].trim());
                }

                return ids;
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to get news: ", e);
        }

        return new long[0];
    }

    public static String news(long id) {
        try {
            String response = sendHttpRequest(String.format(ITEM_URL_TEMPLATE, id));

            if (response != null) {
                String jsonBody = response.replaceAll("\\\\\"", "'");
                Pattern titlePattern = Pattern.compile("\"title\"\\s*:\\s*\"([^\"]*)\"");
                Matcher matcher = titlePattern.matcher(jsonBody);

                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to get news " + id + ":", e);
        }

        return null;
    }

    private static String sendHttpRequest(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == SUCCESSFUL_RESPONSE) {
            return response.body();
        }

        return null;
    }
}
