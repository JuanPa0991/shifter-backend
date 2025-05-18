package com.shift.shift_planner_backend.commons.email;

public enum EmailTemplate {
    RESET_PASSWORD("reset-password.html"),
    USER_CREATED("user-created.html");

    private final String templateName;

    EmailTemplate(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return templateName;
    }
}