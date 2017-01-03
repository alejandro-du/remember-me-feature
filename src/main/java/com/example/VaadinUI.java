package com.example;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author Alejandro Duarte.
 */
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        if (!AuthService.isAuthenticated()) {
            showPublicComponent();
        } else {
            showPrivateComponent();
        }
    }

    public void showPublicComponent() {
        setContent(new PublicComponent());
    }

    public void showPrivateComponent() {
        setContent(new PrivateComponent());
    }

}
