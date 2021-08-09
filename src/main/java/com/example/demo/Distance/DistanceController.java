package com.example.demo.Distance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/distance")
public class DistanceController {

    @PostMapping()
    public DistanceDTO home(@RequestParam("image") MultipartFile file) throws IOException {

        String classId = getClassIDFromName(file.getOriginalFilename());
        String uuid = getUUIDFromName(file.getOriginalFilename());

        RequestBody body = new MultipartBuilder()
                .addFormDataPart("image", file.getName()+ ".jpg", RequestBody.create(MediaType.parse("media/type"), multipartToFile(file,"image.jpg")))
                .type(MultipartBuilder.FORM)
                .build();

        Request request = new Request.Builder()
                .url("http://172.31.41.104:5000")
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        DistanceDTO distanceDTO = objectMapper.readValue(responseBody, DistanceDTO.class);
        distanceDTO.setClassId(classId);
        distanceDTO.setUUID(uuid);
        return distanceDTO;
    }

    public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    private String getClassIDFromName(String name) {
        String[] result = name.split("[$]");
        return result[0];
    }
    private String getUUIDFromName(String name) {
        String[] result = name.split("[$]");
        result = result[1].split("[.]");
        return result[0];
    }

}
