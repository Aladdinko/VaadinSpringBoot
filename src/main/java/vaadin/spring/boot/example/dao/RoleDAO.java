package vaadin.spring.boot.example.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import vaadin.spring.boot.example.dao.config.HibernateUtil;
import vaadin.spring.boot.example.domain.Role;

import java.util.List;

/**
 * Created by maggouh on 02/02/17.
 */
public class RoleDAO {

    public List<Role> getAllRoles() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "from Role order by id");
        return query.list();
    }
}
