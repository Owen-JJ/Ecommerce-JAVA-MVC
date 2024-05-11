package mvc.java.service;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String mes) {
        super(mes);
    }
}