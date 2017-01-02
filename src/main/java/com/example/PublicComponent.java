package com.example;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

/**
 * @author Alejandro Duarte.
 */
public class PublicComponent extends CustomComponent {

    public PublicComponent() {
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(this::onLogin);

        VerticalLayout layout = new VerticalLayout(loginForm);
        layout.setSizeFull();
        layout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
        setCompositionRoot(layout);
        setSizeFull();
    }

    private void onLogin(LoginForm.LoginEvent loginEvent) {
        String username = loginEvent.getLoginParameter("username");
        String password = loginEvent.getLoginParameter("password");

        if (Backend.login(username, password)) {
            VaadinUI ui = (VaadinUI) UI.getCurrent();
            VaadinSession.getCurrent().setAttribute("username", username);
            ui.showPrivateComponent();
        } else {
            Notification.show("Invalid credentials (for demo use: admin/password)", Notification.Type.ERROR_MESSAGE);
        }
    }


}
