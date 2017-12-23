package com.example.duti.chartdemo.model;



public class Task {
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskVenue() {
        return taskVenue;
    }

    public void setTaskVenue(String taskVenue) {
        this.taskVenue = taskVenue;
    }

    public String getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(String taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }

    public String getTaskCompletedDate() {
        return taskCompletedDate;
    }

    public void setTaskCompletedDate(String taskCompletedDate) {
        this.taskCompletedDate = taskCompletedDate;
    }

    private String userId;
    private String taskDetails;
    private String taskVenue;
    private String taskAssignedDate;
    private String taskCompletedDate;
}
