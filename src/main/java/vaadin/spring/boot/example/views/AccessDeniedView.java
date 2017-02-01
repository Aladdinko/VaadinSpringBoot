package vaadin.spring.boot.example.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by Maggouh on 26/01/17.
 */
public class AccessDeniedView extends VerticalLayout implements View {

    private Label message;

    public AccessDeniedView() {
        setMargin(true);
        addComponent(message = new Label());
        message.setSizeUndefined();
        message.addStyleName(ValoTheme.LABEL_FAILURE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        message.setValue(String.format("You do not have access to this view: %s", event.getViewName()));
    }
}
