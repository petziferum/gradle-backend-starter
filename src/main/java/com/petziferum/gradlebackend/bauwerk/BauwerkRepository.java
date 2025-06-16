package com.petziferum.gradlebackend.bauwerk;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BauwerkRepository  extends JpaRepository<Bauwerk, String> {
}
