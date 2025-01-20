package generate.random;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static page.objects.Config.AUTHORISATION_URL;
import static page.objects.Config.DELETE_URL;

public class UserApi {


    //private static final String BASE_URL = "https://yourapi.com/api/users"; // Замените на ваш URL API
    private final HttpClient httpClient;

    public UserApi() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public void createUser(String email, String password, String name) {
        String json = String.format("{\"email\":\"%s\", \"password\":\"%s\", \"name\":\"%s\"}", email, password, name);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTHORISATION_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to create user: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String email) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DELETE_URL)) // Предполагается, что удаление происходит по email
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 401) {
                throw new RuntimeException("Failed to delete user: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
