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
@Table(name = "studies")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Study's title can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Title can not contain any non-letter!")
    private String title;
    @NotBlank(message = "Description can not be null!")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Description can not contain any non-letter!")
    private String description;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<AdviserStudy> adviserStudies;
}
