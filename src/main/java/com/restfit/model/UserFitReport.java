package com.restfit.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Embeddable
@Immutable
@Table(name = "UserFitReport")
public class UserFitReport {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @Column(name = "sumdistance")
    private double sumdistance;

    @Column(name = "avgruntime")
    private double avgruntime;
    
    @Column(name = "avgspeed")
    private double avgspeed;
    
    @Column(name = "week")
    private String week;
    
    @ManyToOne
    @JoinColumn(name = "userfit_id", nullable = false)
    private User user;
    
    public double getSumdistance() {
		return sumdistance;
	}

	public void setSumdistance(double sumdistance) {
		this.sumdistance = sumdistance;
	}

	public double getAvgruntime() {
		return avgruntime;
	}

	public void setAvgruntime(double avgruntime) {
		this.avgruntime = avgruntime;
	}

	public double getAvgspeed() {
		return avgspeed;
	}

	public void setAvgspeed(double avgspeed) {
		this.avgspeed = avgspeed;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
