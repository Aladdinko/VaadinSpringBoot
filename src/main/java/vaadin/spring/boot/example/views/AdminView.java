package vaadin.spring.boot.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import vaadin.spring.boot.example.Sections;
import vaadin.spring.boot.example.backend.MyBackend;

/**
 * Created by Maggouh on 26/01/17.
 */
@Secured("ROLE_ADMIN")
@SpringView(name = "admin")
@SideBarItem(sectionId = Sections.VIEWS, caption = "Admin View")
@FontAwesomeIcon(FontAwesome.COGS)
public class AdminView extends CustomComponent implements View{

    private MyBackend backend;

    @Autowired
    public AdminView(MyBackend backend) {
        this.backend = backend;
        Button button = new Button("Admin caller", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Notification.show(AdminView.this.backend.adminOnlyEcho("Hello Admin World"));
            }
        });
        setCompositionRoot(button);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
