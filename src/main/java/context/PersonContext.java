package context;

public class PersonContext {
    //ThreadLocal variable to store the random first name, last name and email address
    private final ThreadLocal<String> randomFirstName = ThreadLocal.withInitial(()->null);
    private final ThreadLocal<String> randomLastName = ThreadLocal.withInitial(()->null);
    private final ThreadLocal<String> randomEmail = ThreadLocal.withInitial(()->null);

    public void setRandomFirstName(String firstName) {
        randomFirstName.set(firstName);
    }
    public void setRandomLastName(String lastName) {
        randomLastName.set(lastName);
    }
    public void setRandomEmail(String email) {
        randomEmail.set(email);
    }
    public String getRandomFirstName() {
        return randomFirstName.get();
    }
    public String getRandomLastName() {
        return randomLastName.get();
    }
    public String getRandomEmail() {
        return randomEmail.get();
    }


}
