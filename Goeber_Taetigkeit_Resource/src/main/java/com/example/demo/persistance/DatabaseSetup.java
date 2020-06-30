package com.example.demo.persistance;

import com.example.demo.domain.Mitarbeiter;
import com.example.demo.domain.Taetigkeit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class DatabaseSetup {
    @Bean
    CommandLineRunner safeMitarbeiter(MitarbeiterRepository mitarbeiterRepository, TaetigkeitenRepository taetigkeitenRepository){
        List<Mitarbeiter> m = new ArrayList<>();
        m.add(new Mitarbeiter("HUBE", "Franz", "Huber"));
        m.add(new Mitarbeiter("SCMI", "Barbara", "Schmidt"));
        m.add(new Mitarbeiter("BAUE", "Fritz", "Bauer"));

        List<Taetigkeit> t = new ArrayList<>();
        t.add(new Taetigkeit("Implementierung JUnit Tests", LocalDate.of(2019,5,17), 120, m.get(0)));
        t.add(new Taetigkeit("Erstellung UML-Diagramm", LocalDate.of(2019,5,17), 90, m.get(1)));
        t.add(new Taetigkeit("Projektmeeting", LocalDate.of(2019,5,18), 60, m.get(0)));
        t.add(new Taetigkeit("Projektmeeting",  LocalDate.of(2019,5,18), 60, m.get(1)));
        t.add(new Taetigkeit("Projektmeeting",  LocalDate.of(2019,5,18), 60, m.get(2)));
        t.add(new Taetigkeit("Implementierung",  LocalDate.of(2019,5,19), 350, m.get(2)));
        t.add(new Taetigkeit("Implementierung",  LocalDate.of(2019,5,19), 420, m.get(1)));
        t.add(new Taetigkeit("Tests und Bugfixes",  LocalDate.of(2019,5,19), 300, m.get(0)));

        return args -> {
            m.forEach(mitarbeiterRepository::save);
            t.forEach(taetigkeitenRepository::save);
        };
    }
}
