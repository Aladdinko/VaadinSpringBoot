package vaadin.spring.boot.example.domain;

/**
 * Created by maggouh on 02/02/17.
 */

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "USER_ACCOUNT")
public class Account implements UserDetails {
    private long id;

    private String username;
    private String password;

    private List<Role> authorities;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    @Id
    @GeneratedValue(generator = "S_USER_ID")
    @SequenceGenerator(name = "S_USER_ID", sequenceName = "S_USER_ACCOUNT_ID_0", allocationSize = 1)
    @Column(name = "ID")

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    @NotBlank(message = "Username is mandatory")
    @Size(max = 255, message = "Username must be less than {max} characters")
    @Column(name = "USERNAME", unique = true, nullable = false, length = 255)

    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }


    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 12, message = "the password must be between {min} and {max} characters")
    @Column(name = "PASSWORD", nullable = false)

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ACCOUNT_ROLE_GROUP", joinColumns = {
            @JoinColumn(name = "ACCOUNT_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)})
    public List<Role> getAuthorities(){ return this.authorities; }
    public void setAuthorities(List<Role> authorities) {this.authorities = authorities;}



    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
