package com.example.patientsmvc;

import com.example.patientsmvc.entities.Patient;
import com.example.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }
    // this will be executed on startup because of bean
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args -> {

            //to create new clients
            patientRepository.save(

                    new Patient(null,"Issam", new Date(),false,12)
            );
            patientRepository.save(

                    new Patient(null,"Ilyas", new Date(),true,13)
            );
            patientRepository.save(

                    new Patient(null,"Doha", new Date(),false,14)
            );
            patientRepository.save(

                    new Patient(null,"Salma", new Date(),true,15)
            );

            //to loop on values and print them
            patientRepository.findAll().forEach(patient -> {
                System.out.println(patient.getNom());
            });

        };

    }
}


//http://localhost:8082/h2-console
//jdbc:h2:mem:patients_db