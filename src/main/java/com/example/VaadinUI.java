package com.example;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * @author Alejandro Duarte.
 */
public class VaadinUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Object username = VaadinSession.getCurrent().getAttribute("username");
        if (username == null) {
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
