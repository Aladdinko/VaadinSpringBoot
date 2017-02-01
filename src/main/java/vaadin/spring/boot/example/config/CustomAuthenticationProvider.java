package vaadin.spring.boot.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import vaadin.spring.boot.example.account.Account;
import vaadin.spring.boot.example.dao.AccountDAO;

import java.util.Collection;

/**
 * Created by maggouh on 31/01/17.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AccountDAO accountDAO;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName().trim().toLowerCase();
        String password = authentication.getCredentials().toString().trim();

        Account user = accountDAO.findAccountByUsername(username);

        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {

            throw new BadCredentialsException("Username not found.");

        }

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}