package generate.random;

import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import page.objects.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static page.objects.Config.AUTHORISATION_URL;
import static page.objects.Config.DELETE_URL;

public class UserApi {


    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public UserApi() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public User createUser() {
        User user = RandomUser.getRandomUser();

        try {

            String json = objectMapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(AUTHORISATION_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to create user: " + response.body());
            }
            return user;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] serializeUser(User user) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(user);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(String email) {
        User user = new User(email, null, null);
        byte[] userBytes = serializeUser(user);
        if (userBytes == null) {
            throw new RuntimeException("Failed to serialize user for deletion");
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DELETE_URL)) // Предполагается, что удаление происходит по email
                .DELETE()
                .method("DELETE", HttpRequest.BodyPublishers.ofByteArray(userBytes))
                .build();
                System.out.println("delete success");

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
