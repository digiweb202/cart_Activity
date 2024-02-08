package com.mart.cart_activity.Observer;

import java.util.Observable;

import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;

public class CartObserver {
    private static CartObserver instance;
    private Set<Observer> observers = new HashSet<>();
    private Set<CartAdapterListener> adapterListeners = new HashSet<>();

    private CartObserver() {
    }

    public static CartObserver getInstance() {
        if (instance == null) {
            instance = new CartObserver();
        }
        return instance;
    }

    public interface Observer {
        void onCartUpdated();
    }

    public interface CartAdapterListener {
        void onQuantityChanged(int position, int quantity);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void addCartAdapterListener(CartAdapterListener listener) {
        adapterListeners.add(listener);
    }

    public void removeCartAdapterListener(CartAdapterListener listener) {
        adapterListeners.remove(listener);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.onCartUpdated();
        }
    }

    public void notifyCartAdapterListeners(int position, int quantity) {
        for (CartAdapterListener listener : adapterListeners) {
            listener.onQuantityChanged(position, quantity);
        }
    }
}
