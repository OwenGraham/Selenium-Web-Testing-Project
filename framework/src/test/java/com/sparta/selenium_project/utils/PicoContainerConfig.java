package com.sparta.selenium_project.utils;

import com.sparta.selenium_project.utils.DriverManager;
import org.openqa.selenium.WebDriver;
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

