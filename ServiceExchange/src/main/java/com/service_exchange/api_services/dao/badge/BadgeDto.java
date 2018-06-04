package com.service_exchange.api_services.dao.badge;

public class BadgeDto {

    private Integer id;
    private String name;
    private String image;
    private String description;
    private String timeNeeded;
    private String type;
    private String addedByAdminEmail;
    //private Collection<UserBadge> userBadgeCollection;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeNeeded() {
        return timeNeeded;
    }

    public void setTimeNeeded(String timeNeeded) {
        this.timeNeeded = timeNeeded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddedByAdminEmail() {
        return addedByAdminEmail;
    }

    public void setAddedByAdminEmail(String addedByAdminEmail) {
        this.addedByAdminEmail = addedByAdminEmail;
    }
}

