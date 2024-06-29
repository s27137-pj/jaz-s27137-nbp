package pl.javastart.jazs27137nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.jazs27137nbp.model.Waluta;
import pl.javastart.jazs27137nbp.repository.JazRepository;
import pl.javastart.jazs27137nbp.service.JazService;

@RestController
public class JazController {

    private final JazService jazService;
    private final JazRepository jazRepository;

    public JazController(JazService jazService, JazRepository jazRepository) {
        this.jazService = jazService;
        this.jazRepository = jazRepository;
    }


    @Operation(summary = "Wyszukaj sredni kurs wybranej waluty w wybranym przez siebie okresie czasu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request - Request was bad", content = @Content)
    })
    @GetMapping("/id6/{code}/{start}/{stop}")
    public ResponseEntity<Waluta> getDaysAverage(@PathVariable String code, @PathVariable String start, @PathVariable String stop){
        return ResponseEntity.ok(jazService.getDaysNewAverage(code, start, stop));
    }

}
