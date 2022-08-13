package com.notifications.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("notifications")
public class Notification {
    @Id
    private String id;

    private String text;

    private String type;

    /**
     * Define TTL
     */
    @Transient
    private final int ttl = 604800;

    /**
     * Date field with TTL index, entry will be removed after specified time
     */
    @Indexed(name="date", expireAfterSeconds=ttl)
    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
