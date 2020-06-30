package com.example.demo.api;

import com.example.demo.api.exception.GeneralException;
import com.example.demo.domain.Mitarbeiter;
import com.example.demo.domain.Taetigkeit;
import com.example.demo.persistance.MitarbeiterRepository;
import com.example.demo.persistance.TaetigkeitenRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class MitarbeiterController {
    private final MitarbeiterRepository mitarbeiterRepository;
    private final TaetigkeitenRepository taetigkeitenRepository;

    public MitarbeiterController(MitarbeiterRepository mitarbeiterRepository, TaetigkeitenRepository taetigkeitenRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.taetigkeitenRepository = taetigkeitenRepository;
    }

    @GetMapping("/employees")
    List<Mitarbeiter> getAll(){
        return mitarbeiterRepository.findAll();
    }

    @PostMapping("/employees")
    ResponseEntity<Mitarbeiter> newMitarbeiter(@Valid @RequestBody Mitarbeiter mitarbeiter){
        Mitarbeiter saved = mitarbeiterRepository.save(mitarbeiter);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(saved.getId());
        return ResponseEntity
                .created(uri)
                .body(saved);
    }

    @GetMapping("/employees/totalworkingtime/{empid}")
    Integer getWorkingTime(@PathVariable String empid){
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findById(empid)
                .orElseThrow(() -> new GeneralException("Mitarbeiter not found"));
        return taetigkeitenRepository.getTotalWorkingTimeByEmpID(mitarbeiter.getId());
    }

    @GetMapping("/employees/activites/{empid}/{startdate}/{enddate}")
    List<Taetigkeit> taetigkeitenFromMitarbeiterBetweenDates(@PathVariable("empid")String empid,
                                                             @PathVariable("startdate")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate startdate,
                                                             @PathVariable("enddate")@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate enddate){
        return taetigkeitenRepository.getAllByMitarbeiter_IdAndDatumBetween(empid,startdate,enddate);
    }

    @DeleteMapping("/employees/{id}")
    Integer deleteMitarbeiter(@PathVariable String id){
        Mitarbeiter mitarbeiter = mitarbeiterRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Mitarbeiter not found"));
        Integer sum = taetigkeitenRepository.countTaetigkeitsByMitarbeiter_Id(id);
        taetigkeitenRepository.deleteAllByMitarbeiter_Id(id);
        mitarbeiterRepository.delete(mitarbeiter);
        return sum;
    }
}
