package com.castlabs.mp4info;

import java.util.ArrayList;

public class Mp4Box {
  public String type;
  public int size;
  public ArrayList<Mp4Box> subBoxes;

  public Mp4Box(String type, int size) {
    subBoxes = new ArrayList<>();
    this.type = type;
    this.size = size;
  }
}
