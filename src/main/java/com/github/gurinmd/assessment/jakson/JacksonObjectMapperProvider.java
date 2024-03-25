package com.github.gurinmd.assessment.jakson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Singleton, storing ObjectMapper for serializing/deserializing JSON. 
 * Implemented in thread-unsafe manner, because there is no need to work in concurrent environment
 */
public class JacksonObjectMapperProvider {
  
  private JacksonObjectMapperProvider() { }
  
  private static ObjectMapper instance;
  
  public static ObjectMapper getInstance() {
    if (instance == null) {
      instance = new ObjectMapper();
      instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      instance.enable(SerializationFeature.INDENT_OUTPUT);
    }
    return instance;
  }
  
  
}
