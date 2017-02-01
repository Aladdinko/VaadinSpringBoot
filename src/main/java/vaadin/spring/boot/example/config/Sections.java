package vaadin.spring.boot.example.config;

import org.springframework.stereotype.Component;
import org.vaadin.spring.sidebar.annotation.SideBarSection;
import org.vaadin.spring.sidebar.annotation.SideBarSections;

/**
 * Created by maggouh on 01/02/17.
 */
@Component
@SideBarSections({
        @SideBarSection(id = Sections.VIEWS, caption = "Views"),
        @SideBarSection(id = Sections.OPERATIONS, caption = "Operations")
})
public class Sections {

    public static final String VIEWS = "views";
    public static final String OPERATIONS = "operations";
}
