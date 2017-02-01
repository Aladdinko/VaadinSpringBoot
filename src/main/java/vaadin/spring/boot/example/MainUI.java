package vaadin.spring.boot.example;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.security.VaadinSecurity;
import org.vaadin.spring.security.util.SecurityExceptionUtils;
import org.vaadin.spring.sidebar.components.ValoSideBar;
import org.vaadin.spring.sidebar.security.VaadinSecurityItemFilter;
import vaadin.spring.boot.example.views.AccessDeniedView;
import vaadin.spring.boot.example.views.ErrorView;

/**
 * Created by Maggouh on 26/01/17.
 */
@SpringUI(path = "")
@Theme(ValoTheme.THEME_NAME)
@Push(transport = Transport.WEBSOCKET_XHR)
public class MainUI extends UI {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    VaadinSecurity vaadinSecurity;

    @Autowired
    SpringViewProvider springViewProvider;

    @Autowired
    ValoSideBar valoSideBar;


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Vaadin Spring Security");

        setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent errorEvent) {
                if(SecurityExceptionUtils.isAccessDeniedException(errorEvent.getThrowable())) {
                    Notification.show("You don't have access to do that");
                } else {
                    super.error(errorEvent);
                }
            }
        });
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        valoSideBar.setItemFilter(new VaadinSecurityItemFilter(vaadinSecurity));
        layout.addComponent(valoSideBar);

        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();
        layout.addComponent(viewContainer);
        layout.setExpandRatio(viewContainer, 1f);

        Navigator navigator = new Navigator(this, viewContainer);
        // Without an AccessDeniedView, the view provider would act like the restricted views did not exist at all.
        springViewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        navigator.addProvider(springViewProvider);
        navigator.setErrorView(ErrorView.class);
        navigator.navigateTo(navigator.getState());

        setContent(layout); // Call this here because the Navigator must have been configured before the Side Bar can be attached to a UI.
    }
}
