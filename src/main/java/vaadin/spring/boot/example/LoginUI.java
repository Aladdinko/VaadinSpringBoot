package vaadin.spring.boot.example;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;


/**
 * Created by Maggouh on 26/01/17.
 */
@SpringUI(path = "/login")
@Theme(ValoTheme.THEME_NAME)
public class LoginUI extends UI {

    @Autowired
    VaadinSharedSecurity vaadinSharedSecurity;

    private TextField username;
    private PasswordField password;
    private Button login;

    private Label loginFialedLabel;
    private Label loggedOutLabel;

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Vaadin Spring Boot Example of athentication");

        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();

        username = new TextField("Username : ");
        password = new PasswordField("Password : ");

        login = new Button("Login");
        loginForm.addComponent(username);
        loginForm.addComponent(password);
        loginForm.addComponent(login);

        loginForm.addStyleName(ValoTheme.BUTTON_PRIMARY);
        login.setDisableOnClick(true);
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                login();
            }
        });
        VerticalLayout loginLayout = new VerticalLayout();
        loginLayout.setSpacing(true);
        loginLayout.setSizeUndefined();

        if (request.getParameter("logout") != null) {
            loggedOutLabel = new Label("You have been logged out!");
            loggedOutLabel.addStyleName(ValoTheme.LABEL_SUCCESS);
            loggedOutLabel.setSizeUndefined();
            loginLayout.addComponent(loggedOutLabel);
            loginLayout.setComponentAlignment(loggedOutLabel, Alignment.BOTTOM_CENTER);
        }

        loginLayout.addComponent(loginFialedLabel = new Label());
        loginLayout.setComponentAlignment(loginFialedLabel, Alignment.BOTTOM_CENTER);
        loginFialedLabel.setSizeUndefined();
        loginFialedLabel.addStyleName(ValoTheme.LABEL_FAILURE);
        loginFialedLabel.setVisible(false);

        loginLayout.addComponent(loginForm);
        loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

        VerticalLayout rootLayout = new VerticalLayout(loginLayout);
        rootLayout.setSizeFull();
        rootLayout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);
        setContent(rootLayout);
        setSizeFull();
    }

    private void login() {

        try {
            vaadinSharedSecurity.login(username.getValue(),password.getValue());
//            VaadinSession.getCurrent().setAttribute("user", username);
        } catch (AuthenticationException e) {
            username.focus();
            username.selectAll();
            password.setValue("");
            loginFialedLabel.setValue(String.format("Login failed: %s",e.getMessage()));
            loginFialedLabel.setVisible(true);
            if(loggedOutLabel != null) {
                loggedOutLabel.setVisible(false);
            }
        } catch (Exception e) {
            Notification.show("An unexepted error occured", e.getMessage(), Notification.Type.ERROR_MESSAGE);
            LoggerFactory.getLogger(getClass()).error("Unexepted error while loogin in", e);
        } finally {
            login.setEnabled(true);
        }
    }
}
