package com.example.patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
@Entity // defines a JPA entity
//from lombok
@Data  // @data generates getters and setters and default constructor (protected can't be used )
@AllArgsConstructor // generates constructor with all args
@NoArgsConstructor //generates a public default constructor


public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //To auto generate an id
    private Long id;
    private String nom;
    @Temporal(TemporalType.DATE) // to get date in date format instead of timestamp
    private Date dateNaissance;
    private boolean malade;
    private int score;

}
