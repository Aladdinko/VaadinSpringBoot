package vaadin.spring.boot.example.account;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 31/01/17.
 */
@Repository
public class DaoAccountRepository implements AccountRepository {

    @Override
    public void createAccount(Account account) throws UsernameAlreadyUsedException {

    }

    @Override
    public Account findAccountByUsername(String username) {

        Role role = new Role();
        role.setName(Role.ROLE_ADMIN);
        Role role1 = new Role();
        role1.setName(Role.ROLE_USER);
        Role role2 = new Role();
        role2.setName(Role.ROLE_TRAINEE);

        List<Role> rolesAdmin = new ArrayList<Role>();
        rolesAdmin.add(role);
        rolesAdmin.add(role1);
        rolesAdmin.add(role2);

        List<Role> rolesUser = new ArrayList<Role>();
        rolesUser.add(role1);

        Account admin = new Account("admin","admin", rolesAdmin);
        Account user = new Account("user", "user", rolesUser);

        return admin;

    }
}
