package com.github.peek4bUh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author peek4bUh
 */
public class ReadFile {

  /**
   * Reads given resource file as a string.
   *
   * @param fileName path to the resource file
   * @return the file's contents into a String
   */
  public static String getResourceFileAsString(String fileName) {
    ClassLoader classLoader = ReadFile.class.getClassLoader();
    try ( var inputStream = classLoader.getResourceAsStream(fileName)) {
      if (inputStream == null) {
        throw new IllegalArgumentException("File not found: " + fileName);
      }
      // Use BufferedReader for reading line by line
      try ( var reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file", e);
    }
  }

  public static List<String> getResourceFileAsList(String fileName) {
    List<String> myList = new ArrayList<>();

    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream is = classloader.getResourceAsStream(fileName);
      InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);

      for (String line; (line = reader.readLine()) != null;) {
        myList.add(line);
      }

    } catch (IOException ex) {
      Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
    }

    return myList;

  }

}
