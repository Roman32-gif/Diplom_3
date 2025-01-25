package generate.random;
import com.github.javafaker.Faker;
import page.objects.User;

public class RandomUser {
    public static User getRandomUser () {
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        return new User(name, email, password);
    }
}