package com.example.demo.Nav;

import com.example.demo.Instruction.Instruction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.RequestBody;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.squareup.okhttp.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/nav")
public class NavController {

    private final NavService service;

    //@PostMapping("")
    //public Instruction getInstructionsFromPoint(@RequestBody Nav coords) {
        // return service.getInstructions(coords);
    //}

    @PostMapping("/distance")
    public DistanceDTO home(@RequestParam("image") MultipartFile file) throws IOException {

        RequestBody body = new MultipartBuilder()
                .addFormDataPart("image", file.getName()+ ".jpg", RequestBody.create(MediaType.parse("media/type"), multipartToFile(file,"image.jpg")))
                .type(MultipartBuilder.FORM)
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:5000")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        DistanceDTO distanceDTO = objectMapper.readValue(responseBody, DistanceDTO.class);
        return distanceDTO;
    }

    public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }
}
