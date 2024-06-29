package pl.javastart.jazs27137nbp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Waluta {

    @Schema(name="id", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name="kod waluty", example = "EUR", required = true)
    private String code;

    @Schema(name="kurs", example = "4.4566", required = true)
    private Double kurs;

    @Schema(name="od kiedy(data)", example = "2024-05-05", required = true)
    private String dataStart;

    @Schema(name="do kiedy(data)", example = "2024-06-30", required = true)
    private String dataStop;

    @Schema(name="data wykonania zapytania", example = "2024-06-28 20:00:12", required = true)
    private LocalDateTime data = LocalDateTime.now();

    public Waluta() {
    }

    public Waluta(Long id, String code, Double kurs, String dataStart, LocalDateTime data, String dataStop) {
        this.id = id;
        this.code = code;
        this.kurs = kurs;
        this.dataStart = dataStart;
        this.data = data;
        this.dataStop = dataStop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(String dataStart) {
        this.dataStart = dataStart;
    }

    public String getDataStop() {
        return dataStop;
    }

    public void setDataStop(String dataStop) {
        this.dataStop = dataStop;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

