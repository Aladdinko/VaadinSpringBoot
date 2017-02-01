package vaadin.spring.boot.example.operations;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import vaadin.spring.boot.example.Sections;
import vaadin.spring.boot.example.backend.MyBackend;

/**
 * Created by maggouh on 01/02/17.
 */
@SpringComponent
@SideBarItem(sectionId = Sections.OPERATIONS, caption = "User operation", order = 1)
@FontAwesomeIcon(FontAwesome.USER)
public class UserOperation implements Runnable {

    private final MyBackend backend;

    @Autowired
    public UserOperation(MyBackend backend) {
        this.backend = backend;
    }

    @Override
    public void run() {
        Notification.show(backend.echo("Hello World"));
    }
}
