package com.example.patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity // defines a JPA entity
//from lombok
@Data  // @data generates getters and setters and default constructor (protected can't be used )
@AllArgsConstructor // generates constructor with all args
@NoArgsConstructor //generates a public default constructor


public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //To auto generate an id
    private Long id;
    @NotEmpty //constraints for name
    @Size(min=4 ,max=40)
    private String nom;
    @Temporal(TemporalType.DATE) // to get date in date format instead of timestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")  // in case the date format of the inserted causes a problem
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("1")
    private int score;

}
