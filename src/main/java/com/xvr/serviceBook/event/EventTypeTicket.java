package com.xvr.serviceBook.event;

import lombok.Getter;

@Getter
@Deprecated
public enum EventTypeTicket {
    CREATE("create", "createMailEvent"),
    UPDATE("update", "updateMailEvent"),
    DELETE("delete", "deleteMailEvent");

    private final String modificationType;
    private final String emailName;

    EventTypeTicket(String modificationType, String emailName) {
        this.modificationType = modificationType;
        this.emailName = emailName;
    }

}
