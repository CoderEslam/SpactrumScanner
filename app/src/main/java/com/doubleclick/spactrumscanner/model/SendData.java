package com.doubleclick.spactrumscanner.model;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class SendData {

    private String pc_id;
    private String user_id;
    private String notes;

    public SendData(String pc_id, String user_id, String notes) {
        this.pc_id = pc_id;
        this.user_id = user_id;
        this.notes = notes;
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
}
