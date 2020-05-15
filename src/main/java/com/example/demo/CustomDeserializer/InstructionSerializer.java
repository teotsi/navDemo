package com.example.demo.CustomDeserializer;

import com.example.demo.Instruction.Instruction;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.shapes.GHPoint;
import com.graphhopper.util.shapes.GHPoint3D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utilities.utilities.getAngle;

public class InstructionSerializer extends JsonSerializer<Instruction> {

    @Override
    public void serialize(Instruction instruction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("distance", instruction.getDistance());
        jsonGenerator.writeNumberField("time", instruction.getTime());
        List<Instruction> instructionList = new ArrayList<>();
        InstructionList list = instruction.getInstructions();
        jsonGenerator.writeArrayFieldStart("instructions");
        for (int i = 0, listSize = list.size(); i < listSize; i++) {
            com.graphhopper.util.Instruction currentInstruction = list.get(i); //iterating over each instruction
            jsonGenerator.writeStartObject(); //opening bracket
            jsonGenerator.writeNumberField("sign", currentInstruction.getSign());
            jsonGenerator.writeStringField("name", currentInstruction.getName());
            jsonGenerator.writeNumberField("distance", currentInstruction.getDistance());
            jsonGenerator.writeNumberField("time", currentInstruction.getTime());

            PointList pointList = currentInstruction.getPoints();

            if (listSize >= 2 && i + 1 < listSize) {
                jsonGenerator.writeStringField("angle", getAngle(pointList.get(0), list.get(i + 1).getPoints().get(0)));
            }

            jsonGenerator.writeArrayFieldStart("points"); //opening array bracket

            for (GHPoint3D point : pointList) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("lat", point.getLat());
                jsonGenerator.writeNumberField("lon", point.getLon());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray(); //closing array bracket

            jsonGenerator.writeEndObject(); //closing instruction object bracket
        }
        jsonGenerator.writeEndArray(); //closing instruction array
        jsonGenerator.writeEndObject();
    }
}
