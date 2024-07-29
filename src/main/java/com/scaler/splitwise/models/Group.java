package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "usergroup")
public class Group extends BaseModel {
    private String name;

    @ManyToMany
    private List<User> members;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;
}
