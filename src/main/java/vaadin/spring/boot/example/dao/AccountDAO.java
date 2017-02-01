package vaadin.spring.boot.example.dao;

import org.springframework.stereotype.Repository;
import vaadin.spring.boot.example.account.Account;
import vaadin.spring.boot.example.account.Role;
import vaadin.spring.boot.example.dao.exception.UsernameAlreadyUsedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maggouh on 31/01/17.
 */
@Repository //annotation for  DAO classes
public class AccountDAO {
    public void createAccount(Account account) throws UsernameAlreadyUsedException {

        }

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

//    public Account findAccountByUsername(String username) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Account account = null;
//        Query query = session.createQuery(
//                "from Account " +
//                        "where TRIM(UPPER(username)) = UPPER(:username)");
//        query.setParameter("username", username);
//        List list = query.list();
//
//        if (list.size() == 1) {
//            account = (Account) list.get(0);
//        }
//        return account;
//    }


}
