package enums;

public enum Enum_1 {

    USER1("mobilyaplan.test.user@gmail.com", "Mobilyaplan.test.user1/2"),
    USER2("user2@gmail.com", "user2Passsword"),
    USER3("user3@gmail.com", "user3Passsword"),
    USER4("user4@gmail.com", "user4Passsword"),
    ;
    private String email;
    private String password;

    Enum_1(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void login() {
        System.out.println("email : " + this.email + " password : " + this.password);
        System.out.println("user logged in with credentials");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
