package com.projectpj.projektpj;

import java.util.List;

import lombok.Data;

@Data
public class Repository {
    private String name;
    private Owner owner;
    private List<Branch> branches;
    private boolean fork;
}
