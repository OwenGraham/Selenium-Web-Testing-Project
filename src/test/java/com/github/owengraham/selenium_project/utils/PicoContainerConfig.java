package com.github.owengraham.selenium_project.utils;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PicoContainerConfig {

    private static final MutablePicoContainer container = new DefaultPicoContainer();

    static {
        // Initialize and configure PicoContainer
        DriverManager driverManager = new DriverManager();
        container.addComponent(DriverManager.class, driverManager);
    }

    public static MutablePicoContainer getContainer() {
        return container;
    }
}

