package com.petziferum.gradlebackend.bauwerk;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.NotActiveException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bauwerk")
public class BauwerkController {

    private BauwerkRepository bauwerkRepository;

    @PostMapping("/save")
    public ResponseEntity<Bauwerk> saveBauwerk(Bauwerk bauwerk) {
        Bauwerk savedBw = bauwerkRepository.save(bauwerk);
        return new ResponseEntity<>(savedBw, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Bauwerk> updateBauwerk(@RequestBody Bauwerk bauwerk) {
        final String BAUWERK_NOT_FOUND = "Bauwerk nicht gefunden mit id: %s";

        Bauwerk dbBauwerk = bauwerkRepository.findById(bauwerk.getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format(BAUWERK_NOT_FOUND, bauwerk.getId())
                ));
        updateBauwerkFields(dbBauwerk, bauwerk);
        updateSchutzeinrichtungen(dbBauwerk, bauwerk);

        return ResponseEntity.ok(bauwerkRepository.save(dbBauwerk));
    }

    private void updateBauwerkFields(Bauwerk dbBauwerk, Bauwerk bauwerk) {
        dbBauwerk.setName(bauwerk.getName());
        dbBauwerk.setBeschreibung(bauwerk.getBeschreibung());
    }

    private void updateSchutzeinrichtungen(Bauwerk dbBauwerk, Bauwerk bauwerk) {
        dbBauwerk.getSchutzeinrichtungen().clear();
        Optional.ofNullable(bauwerk.getSchutzeinrichtungen())
                .ifPresent(schutzeinrichtungen ->
                        dbBauwerk.getSchutzeinrichtungen().addAll(schutzeinrichtungen));
    }

    @GetMapping()
    public ResponseEntity<List<Bauwerk>> getAllBauwerk() {
        List<Bauwerk> allBauwerke = bauwerkRepository.findAll();
        return new ResponseEntity<>(allBauwerke, HttpStatus.OK);
    }
}

