package com.example;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Alejandro Duarte.
 */
@Route("")
public class VaadinUI extends VerticalLayout {

    public VaadinUI() {
        if (!AuthService.isAuthenticated()) {
            showPublicComponent();
        } else {
            showPrivateComponent();
        }
    }

    public void showPublicComponent() {
        removeAll();
        add(new PublicComponent());
    }

    public void showPrivateComponent() {
        removeAll();
        add(new PrivateComponent());
    }

}
