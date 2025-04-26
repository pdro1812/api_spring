package com.project.api.Model;

public class TeamInsight{
    private String teamName;
    private long totalMembers;
    private long leadersCount;
    private long completedProjectsCount;

    public TeamInsight(String teamName, long totalMembers, long leadersCount, long completedProjectsCount) {
        this.teamName = teamName;
        this.totalMembers = totalMembers;
        this.leadersCount = leadersCount;
        this.completedProjectsCount = completedProjectsCount;
    }

    // Getters e Setters
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(long totalMembers) {
        this.totalMembers = totalMembers;
    }

    public long getLeadersCount() {
        return leadersCount;
    }

    public void setLeadersCount(long leadersCount) {
        this.leadersCount = leadersCount;
    }

    public long getCompletedProjectsCount() {
        return completedProjectsCount;
    }

    public void setCompletedProjectsCount(long completedProjectsCount) {
        this.completedProjectsCount = completedProjectsCount;
    }
}
