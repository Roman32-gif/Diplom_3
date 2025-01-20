package generate.random;
import com.github.javafaker.Faker;
import page.objects.User;

public class RandomUser {
    public static User getRandomUser () {
        Faker faker = new Faker();
        String userName = faker.name().firstName();
        String userEmail = faker.internet().emailAddress();
        String userPassword = faker.internet().password();
        return new User(userName, userEmail, userPassword);
    }
}