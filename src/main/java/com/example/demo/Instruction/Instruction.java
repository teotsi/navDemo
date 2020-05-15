package com.example.demo.Instruction;

import com.example.demo.CustomDeserializer.InstructionSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonSerialize(using = InstructionSerializer.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instruction {
    double distance;
    long time;
    InstructionList instructions;
    PointList points;

}
