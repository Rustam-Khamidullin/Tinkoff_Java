package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    @SuppressWarnings({"RegexpSinglelineJava", "UncommentedMain"})
    public static void main(String[] args) {
        for (var i : hackerNewsTopStories()) {
            System.out.println(i + " : " + news(i));
        }
    }

    public static long[] hackerNewsTopStories() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == SUCCESSFUL_RESPONSE) {
                String[] idStrings = response.body().replaceAll("[\\[\\]\"]", "").split(",");

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
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(ITEM_URL_TEMPLATE, id)))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == SUCCESSFUL_RESPONSE) {
                String jsonBody = response.body().replaceAll("\\\\\"", "'");
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
}
