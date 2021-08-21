/** 
 * The code is from edencoding theres an explanation
 * On setting up dependancy and code injection for JavaFX
 * @author Ed Eden-Rump
*/


package com.schoolAdmin.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
// import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class DependancyInjector {
    private static final Map<Class<?>, Callable<?>> injectionMethods = new HashMap<>();

    private static ResourceBundle  bundle = null;

    public static void setBundle(ResourceBundle bundle){
        DependancyInjector.bundle = bundle;
    }

    public static Parent load(String location) throws IOException{
        FXMLLoader loader = getLoader(location);
        return loader.load();
    }

    private static Object constructController(Class<?> controllerClass){
        if(injectionMethods.containsKey(controllerClass)){
            return loadControllerWithSavedMethod(controllerClass);
        } else {
            return loadControllerWithDefaultConstructor(controllerClass);
        }
    }

    public static FXMLLoader getLoader(String location){
        return new FXMLLoader(
            DependancyInjector.class.getResource(location),
            bundle,
            new JavaFXBuilderFactory(),
            controllerClass -> constructController(controllerClass)
        );
    }

    private static Object loadControllerWithSavedMethod(Class<?> controller){
        try{
            return injectionMethods.get(controller).call();
        } catch(Exception e){
            throw new IllegalStateException(e);
        }
    }

    private static Object loadControllerWithDefaultConstructor(Class<?> controller){
        try{
            return controller.getConstructor().newInstance();
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e){
            throw new IllegalStateException(e);
        }
    }

    // public static void addInjectionMethod(Class<?> controller, Callback<Class<?>, Object> method){
    //     injectionMethods.put(controller, method);
    // }

    public static void removeInjectionMethod(Class<?> controller){
        injectionMethods.remove(controller);
    }
}
