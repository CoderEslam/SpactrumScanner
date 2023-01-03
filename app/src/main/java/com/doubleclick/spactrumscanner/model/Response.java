package com.doubleclick.spactrumscanner.model;

import androidx.annotation.NonNull;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class Response {

    private String pc_id;
    private String user_id;
    private String notes;
    private String id;

    public Response(String pc_id, String user_id, String notes, String id) {
        this.pc_id = pc_id;
        this.user_id = user_id;
        this.notes = notes;
        this.id = id;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Response{" +
                "pc_id='" + pc_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", notes='" + notes + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
