package vaadin.spring.boot.example.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by maggouh on 02/02/17.
 */
@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class Role implements GrantedAuthority {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_TRAINEE = "ROLE_TRAINEE";

    private short id;
    private String name;


    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 3, scale = 0, insertable = false, updatable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Column(name = "NAME", unique = true, nullable = false, length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getAuthority() {
        return this.name;
    }
}
