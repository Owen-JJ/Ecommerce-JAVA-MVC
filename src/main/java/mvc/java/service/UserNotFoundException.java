package mvc.java.service;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String mes) {
        super(mes);
    }
}