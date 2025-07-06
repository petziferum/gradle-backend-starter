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

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bauwerk")
public class BauwerkController {

    private BauwerkRepository bwrepo;

    @PostMapping("/save")
    public ResponseEntity<Bauwerk> saveBauwerk(Bauwerk bauwerk) {
        Bauwerk savedBw = bwrepo.save(bauwerk);
        return new ResponseEntity<>(savedBw, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Bauwerk> updateBauwerk(@RequestBody Bauwerk bauwerk) {
        Bauwerk dbBauwerk = bwrepo.findById(bauwerk.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bauwerk not found with id: " + bauwerk.getId()));
        dbBauwerk.setName(bauwerk.getName());
        dbBauwerk.setBeschreibung(bauwerk.getBeschreibung());

        dbBauwerk.getSchutzeinrichtungen().clear();
        if (bauwerk.getSchutzeinrichtungen() != null) {
            dbBauwerk.getSchutzeinrichtungen().addAll(bauwerk.getSchutzeinrichtungen());
        }

        Bauwerk savedBw = bwrepo.save(dbBauwerk);
        return new ResponseEntity<>(savedBw, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Bauwerk>> getAllBauwerk() {
        List<Bauwerk> allBauwerke = bwrepo.findAll();
        return new ResponseEntity<>(allBauwerke, HttpStatus.OK);
    }
}
