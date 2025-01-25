package page.objects;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialization = 1L;
    private final String name;
    private final String email;
    private final String password;

    public User(String userName, String userEmail, String userPassword) {
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
