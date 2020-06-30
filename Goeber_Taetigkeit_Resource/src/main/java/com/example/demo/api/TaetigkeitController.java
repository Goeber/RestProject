package com.example.demo.api;

import com.example.demo.domain.Taetigkeit;
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
public class TaetigkeitController {
    private final TaetigkeitenRepository taetigkeitenRepository;

    public TaetigkeitController(TaetigkeitenRepository taetigkeitenRepository) {
        this.taetigkeitenRepository = taetigkeitenRepository;
    }

    @PostMapping("/activities")
    ResponseEntity<Taetigkeit> addTaetigkeit(@Valid @RequestBody Taetigkeit taetigkeit){
        Taetigkeit saved = taetigkeitenRepository.save(taetigkeit);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(saved.getId());
        return ResponseEntity
                .created(uri)
                .body(saved);
    }

    @GetMapping("/activities/{date}")
    List<Taetigkeit> getAllFromDate(@PathVariable("date") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)LocalDate date){
        return taetigkeitenRepository.getAllByDatum(date);
    }
}
