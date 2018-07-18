package com.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

/**
 * @author Alejandro Duarte.
 */
public class PublicComponent extends VerticalLayout {

    public PublicComponent() {
        TextField username = new TextField("Username");
        username.focus();

        PasswordField password = new PasswordField("Password");

        Checkbox rememberMe = new Checkbox("Remember me");

        Button button = new Button("Login", e -> onLogin(username.getValue(), password.getValue(), rememberMe.getValue()));

        username.addKeyPressListener(Key.ENTER, e -> button.click());
        password.addKeyPressListener(Key.ENTER, e -> button.click());

        FormLayout formLayout = new FormLayout(username, password, button, rememberMe);
        formLayout.setSizeUndefined();

        add(formLayout);
        setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
    }

    private void onLogin(String username, String password, boolean rememberMe) {
        if (AuthService.login(username, password, rememberMe)) {
            VaadinUI ui = (VaadinUI) UI.getCurrent().getChildren().findFirst().get();
            ui.showPrivateComponent();
        } else {
            Notification.show("Invalid credentials (for demo use: admin/password)");
        }
    }

}
