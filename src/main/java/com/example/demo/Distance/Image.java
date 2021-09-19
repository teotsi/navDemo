package com.example.demo.Distance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public @Data
class Image {
    @Id
    private String classId;
    private double lat;
    private double lon;
    private String description;
    private String uuid;
    private double distance;
}
