package com.dot2line.vocabar.util;

import android.support.v4.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import rx.observables.StringObservable;

public class CSVReader {
  private static final String SEPARATOR = "\n";

  InputStream inputStream;

  public CSVReader(InputStream is) {
    this.inputStream = is;
  }

  public static List<Pair<String, String>> read(String path) {
    List<Pair<String, String>> resultList = new ArrayList<>();

    if (path != null) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringObservable.split(StringObservable.from(br), Pattern.compile(SEPARATOR))
            .map(line -> new Pair<String, String>(line.split(",")[0], line.split(",")[1]))
            .subscribe(resultList::add);
      }  catch (IOException e) {
        e.printStackTrace();
      }
    }

    return resultList;
  }
}
