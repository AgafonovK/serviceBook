package com.xvr.serviceBook.entity.enums;

/**
 * ADMIN: все права.
 * USER: обзор и редактирование меню справочников.
 * VIEWER: наблюдатель.
 */
public enum PositionRole {
    ADMIN("ADMIN: + \n" +  "все права."),
    USER("USER: + \n" + " Обзор и редактирование меню справочников."),
    VIEWER("VIEWER: + \n" + "наблюдатель.");

    public final String label;

    private PositionRole(String label) {
        this.label=label;
    }
}
