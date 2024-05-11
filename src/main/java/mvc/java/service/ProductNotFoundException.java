package mvc.java.service;

public class ProductNotFoundException extends Throwable {
    public ProductNotFoundException(String mes) {
        super(mes);
    }
}
