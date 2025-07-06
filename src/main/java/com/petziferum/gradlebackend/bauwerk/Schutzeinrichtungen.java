package com.petziferum.gradlebackend.bauwerk;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bauwerk_schutzeinrichtungen")
public class Schutzeinrichtungen {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    String name;

    String bemerkung;

    @Column(name = "material")
    @ToString.Exclude
    @Convert(converter = MaterialListConverter.class)
    @Builder.Default
    private List<Materialart> material = new ArrayList<>();
}
