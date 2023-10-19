package com.castlabs.mp4info;

import java.util.List;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Mp4InfoTest {
  @Test
  public void testAnalyzeMp4StructureWithMockFiles() throws IOException {
    // Loop through file indices (e.g., case_0, case_1, ...)
    for (int i = 0; i < 2; i++) {
      String jsonFilename = "mock_json_files/case_" + i + ".json";
      String mp4Filename = "mock_mp4_files/case_" + i + ".mp4";

      List<Mp4Box> expectedStructure = readJsonFile(jsonFilename);

      InputStream mockInputStream = getClass().getClassLoader().getResourceAsStream(mp4Filename);
      Mp4Box result = Mp4Info.analyzeMp4Structure(mockInputStream);
      List<Mp4Box> actualStructure = result.subBoxes; // Use result.subBoxes or a relevant getter method

      Mp4InfoAssertions.assertMp4BoxStructure(actualStructure, expectedStructure);
    }
  }

  private List<Mp4Box> readJsonFile(String filename) throws IOException {
    // Create an ObjectMapper for JSON deserialization
    ObjectMapper objectMapper = new ObjectMapper();

    // Use the class loader to get the input stream of the JSON file from the
    // resources directory
    try (InputStream inputStream = Mp4Box.class.getClassLoader().getResourceAsStream(filename)) {
      if (inputStream == null) {
        throw new IOException("File not found: " + filename);
      }

      // Deserialize the JSON into a list of Mp4Box objects
      return objectMapper.readValue(inputStream, new TypeReference<List<Mp4Box>>() {
      });
    }
  }
}