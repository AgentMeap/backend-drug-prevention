package com.group7.swp391.drug_prevention.domain;


import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import com.group7.swp391.drug_prevention.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Manager_id")
    private User manager;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(name = "Program_Coordinator", columnDefinition = "NVARCHAR(255)")
    private String programCoordinator;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String description;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String location;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "Start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "End_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name = "Created_at")
    private Date createdAt;

    @Column(name = "Updated_at")
    private Date updatedAt;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> member;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgramCoordinator() {
        return programCoordinator;
    }

    public void setProgramCoordinator(String programCoordinator) {
        this.programCoordinator = programCoordinator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
