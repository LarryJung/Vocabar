package com.dot2line.vocabar.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CSVReader {
  InputStream inputStream;

  public CSVReader(InputStream is) {
    this.inputStream = is;
  }

  public static ArrayList<String[]> read(String path) {
    ArrayList<String[]> resultList = new ArrayList<>();
    if (path != null) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while((line = br.readLine()) != null) {
          String[] row = line.split(",");
          resultList.add(row);
        }
      }  catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultList;
  }
}
