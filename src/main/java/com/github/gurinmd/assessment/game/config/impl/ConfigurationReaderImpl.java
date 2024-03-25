package com.github.gurinmd.assessment.game.config.impl;

import java.io.FileReader;
import java.io.IOException;

import com.github.gurinmd.assessment.game.config.ConfigurationReader;
import com.github.gurinmd.assessment.game.config.data.Configuration;
import com.github.gurinmd.assessment.jakson.JacksonObjectMapperProvider;

public class ConfigurationReaderImpl implements ConfigurationReader {
  
  @Override
  public Configuration readGameConfiguration(String path) throws IOException {
    try (FileReader fileReader = new FileReader(path)) {
      return JacksonObjectMapperProvider.getInstance().readValue(fileReader, Configuration.class);
    }
  }
}
