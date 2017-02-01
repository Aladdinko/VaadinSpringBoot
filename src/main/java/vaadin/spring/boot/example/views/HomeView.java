package vaadin.spring.boot.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.spring.sidebar.annotation.FontAwesomeIcon;
import org.vaadin.spring.sidebar.annotation.SideBarItem;
import vaadin.spring.boot.example.Sections;

/**
 * Created by Maggouh on 26/01/17.
 */
@SpringView(name="")
@SideBarItem(sectionId = Sections.VIEWS, caption = "Home", order = 0)
@FontAwesomeIcon(FontAwesome.HOME)
public class HomeView extends VerticalLayout implements View{

    public HomeView() {

        setSpacing(true);
        setMargin(true);

        Label header = new Label("Welcome to Home View");
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);
        Label corps = new Label("Welcome ");

        corps.setContentMode(ContentMode.HTML);
        addComponent(corps);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
