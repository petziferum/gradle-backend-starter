package com.petziferum.gradlebackend.bauwerk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bauwerk {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    String bauwerksnummer;

    String name;

    String beschreibung;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bauwerkid")
    @Builder.Default
    List<Schutzeinrichtungen> schutzeinrichtungen = new ArrayList<Schutzeinrichtungen>();
}
