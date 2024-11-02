package com.petziferum.gradlebackend.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response-Objekt für Produktliste")
public class ProductResponse {
    @Schema(description = "Status der Antwort", example = "Erfolg oder Keine Produkte gefunden")
    private String status;

    @Schema(description = "Message des Responses")
    private String message;

    @Schema(description = "Liste der Produkte")
    private List<Product> content;

}

