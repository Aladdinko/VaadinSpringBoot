package vaadin.spring.boot.example.config.backend;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Maggouh on 26/01/17.
 */
public interface MyBackend {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String adminOnlyEcho(String s);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    String echo(String s);



}
