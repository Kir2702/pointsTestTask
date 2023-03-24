package assignment.cosy.demoAPI.query;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

public class Request {
    public static String getData (Map<String, String> queryParam){
        String country = URLEncoder.encode(queryParam.get("country"), StandardCharsets.UTF_8);
        String state = URLEncoder.encode(queryParam.get("state"), StandardCharsets.UTF_8);
        String type = URLEncoder.encode(queryParam.get("type"), StandardCharsets.UTF_8);
        String response = null;

        //область
        if (type.equals("area")){
            String url = "https://nominatim.openstreetmap.org/search.php?country="+country+"&polygon_geojson=1&format=jsonv2&state="+state;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(60))
                    .build();
            response = String.valueOf(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join());
        }

        //округ
        if (type.equals("county")){
            String url1 = "https://nominatim.openstreetmap.org/search?q="+state+"&country="+country+"&format=json&polygon_geojson=1";
            HttpClient client1 = HttpClient.newHttpClient();
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create(url1))
                    .timeout(Duration.ofSeconds(60))
                    .build();
            response = String.valueOf(client1.sendAsync(request1, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).join());
        }


        return response;
    }
}