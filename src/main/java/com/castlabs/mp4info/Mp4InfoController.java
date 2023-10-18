package com.castlabs.mp4info;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Stack;

@RestController
public class Mp4InfoController {

  @GetMapping("/analyze-mp4")
  public Mp4Box analyzeMp4File(@RequestParam String url) {
    try {
      URL mp4Url = new URL(url);
      HttpURLConnection connection = (HttpURLConnection) mp4Url.openConnection();
      connection.setRequestMethod("GET");

      if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
        InputStream inputStream = connection.getInputStream();
        return analyzeMp4Structure(inputStream);
      } else {
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Mp4Box analyzeMp4Structure(InputStream inputStream) throws IOException {
    Mp4Box rootBox = new Mp4Box("root", 0);
    rootBox.subBoxes = new ArrayList<>();

    Stack<Mp4Box> boxStack = new Stack<>();
    boxStack.push(rootBox);

    while (!boxStack.isEmpty()) {
      Mp4Box currentBox = boxStack.peek();

      byte[] header = new byte[8];
      int bytesRead = inputStream.read(header);
      if (bytesRead < 8) {
        break; // No more data to read
      }

      int boxSize = ByteBuffer.wrap(header, 0, 4).getInt();
      String boxType = new String(header, 4, 4);

      Mp4Box box = new Mp4Box(boxType, boxSize);

      currentBox.subBoxes.add(box);

      if (boxType.equals("moof") || boxType.equals("traf")) {
        boxStack.push(box);
        continue;
      }

      long bytesToSkip = boxSize - 8;
      if (bytesToSkip > 0) {
        long skipped = inputStream.skip(bytesToSkip);
        if (skipped != bytesToSkip) {
          break; // Incomplete data
        }
      }

      // If the sum of sizes matches the parent size, pop the current box
      while (currentBox.size > 0 && currentBox.size == 8 + currentBox.subBoxes.stream().mapToLong(b -> b.size).sum()) {
        boxStack.pop();
        if (!boxStack.isEmpty()) {
          currentBox = boxStack.peek();
        }
      }
    }

    return rootBox;
  }
}
