package com.castlabs.mp4info;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class Mp4InfoController {

  @GetMapping("/analyze-mp4")
  public Mono<Mp4Box> analyzeMp4File(@RequestParam String url) {
    return Mono.defer(() -> {
      try {
        return fetchDataFromUrl(url).map(inputStream -> {
          try {
            return Mp4Info.analyzeMp4Structure(inputStream);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
      } catch (IOException e) {
        // Handle exceptions
        return Mono.error(e);
      }
    });
  }

  private Mono<InputStream> fetchDataFromUrl(String url) throws IOException {
    return HttpClient.create()
        .get()
        .uri(url)
        .responseContent()
        .aggregate()
        .asInputStream();
  }
}