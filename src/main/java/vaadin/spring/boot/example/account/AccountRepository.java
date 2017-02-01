package vaadin.spring.boot.example.account;

/**
 * Created by maggouh on 31/01/17.
 */
public interface AccountRepository {
    void createAccount(Account account) throws UsernameAlreadyUsedException;
    Account findAccountByUsername(String username);
}
