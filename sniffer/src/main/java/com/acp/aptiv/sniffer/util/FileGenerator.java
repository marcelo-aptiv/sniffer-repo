package com.acp.aptiv.sniffer.util;

import com.acp.aptiv.sniffer.dto.ActuatorDto;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@UtilityClass
public class FileGenerator {

  private static final String CSV_FILE_NAME = "files/services.csv";

  public Resource getFile(List<ActuatorDto> actuatorDtoList) {
    try {
      generateCsvFile(actuatorDtoList);
      Path filePath = Path.of(CSV_FILE_NAME);
      return new UrlResource(filePath.toUri());
    } catch (MalformedURLException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private void generateCsvFile(List<ActuatorDto> actuatorDtoList) {
    try {
      var csvWrite = new CSVWriter(
          new FileWriter(CSV_FILE_NAME, false),
          ';', CSVWriter.NO_QUOTE_CHARACTER,
          CSVWriter.DEFAULT_ESCAPE_CHARACTER,
          CSVWriter.DEFAULT_LINE_END);

      csvWrite.writeNext(ActuatorDto.HEADER);

      actuatorDtoList.stream()
          .map(ActuatorDto::toCSV)
          .forEach(s -> csvWrite.writeNext(s,
              false));
      csvWrite.flush();

    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
