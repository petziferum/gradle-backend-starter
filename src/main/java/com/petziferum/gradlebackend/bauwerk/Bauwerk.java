package com.petziferum.gradlebackend.bauwerk;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

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
