package vaadin.spring.boot.example.dao.exception;

/**
 * Created by maggouh on 31/01/17.
 */
public class UsernameAlreadyUsedException extends Exception {
    public UsernameAlreadyUsedException(String username) {
        super("The username '" + username + "' is already in use.");
    }
}
