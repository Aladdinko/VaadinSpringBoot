package vaadin.spring.boot.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vaadin.spring.boot.example.account.Account;
import vaadin.spring.boot.example.dao.AccountDAO;

/**
 * Created by maggouh on 31/01/17.
 */
@Service
public class AccountDetailsService implements UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    AccountDAO accountDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountDAO.findAccountByUsername(username);
            return account;
        } catch (UsernameNotFoundException e) {
            LOG.debug("Account not found", e);
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
