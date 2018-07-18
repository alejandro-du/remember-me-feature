package com.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

/**
 * @author Alejandro Duarte.
 */
public class PrivateComponent extends VerticalLayout {

    public PrivateComponent() {
        String username = (String) VaadinSession.getCurrent().getAttribute(AuthService.SESSION_USERNAME);

        H1 label = new H1("Welcome, " + username);

        Button button = new Button("Sign out", e -> onLogout());

        add(label, button);
    }

    private void onLogout() {
        AuthService.logOut();
    }

}
