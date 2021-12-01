package com.xvr.serviceBook.entity.enums;

import lombok.Getter;

/**
 * ADMIN: все права.
 * USER: обзор и редактирование меню справочников.
 * VIEWER: наблюдатель.
 */
@Getter
public enum PositionRole {
    ADMIN("ADMIN: \n" + "все права."),
    USER("USER: \n" + " Обзор и редактирование меню справочников."),
    VIEWER("VIEWER: \n" + "наблюдатель.");

    public final String label;

    PositionRole(String label) {
        this.label = label;
    }
}
