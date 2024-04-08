package com.sau.dims.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Adviser's name can not be null!")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name can not contain any non-letter!")
    private String name;
    @NotBlank(message = "Department can not be null!")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Department can not contain any non-letter!")
    private String department;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<AdviserStudy> adviserStudies;


}
