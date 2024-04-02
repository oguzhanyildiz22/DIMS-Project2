package com.sau.dims.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advisers")
public class Adviser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String department;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<AdviserStudy> adviserStudies;


}
