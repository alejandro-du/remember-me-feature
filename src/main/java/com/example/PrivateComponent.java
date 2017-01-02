package com.example;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Alejandro Duarte.
 */
public class PrivateComponent extends CustomComponent {

    public PrivateComponent() {
        String username = (String) VaadinSession.getCurrent().getAttribute("username");
        Label label = new Label("Welcome, " + username);
        label.addStyleName(ValoTheme.LABEL_HUGE);

        Button button = new Button("Sign out", this::onLogout);

        VerticalLayout layout = new VerticalLayout(label, button);
        setCompositionRoot(layout);
    }

    private void onLogout(Button.ClickEvent event) {
        VaadinSession.getCurrent().close();
        Page.getCurrent().setLocation("");
    }

}
