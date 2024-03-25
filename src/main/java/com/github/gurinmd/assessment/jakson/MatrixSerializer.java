package com.github.gurinmd.assessment.jakson;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * Weird serializer to make Jackson print readable matrices
 */
public class MatrixSerializer extends StdSerializer<List<List<String>>> {
  public MatrixSerializer() {
    this(null);
  }
  
  public MatrixSerializer(Class<List<List<String>>> clazz) {
    super(clazz);
  }

  @Override
  public void serialize(List<List<String>> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    jgen.writeStartArray();
    jgen.writeRaw("\n");
    jgen.writeRaw(formatMatrix(value));
    jgen.writeEndArray();
  }

  /**
   * Format matrix. Because Jackson does not know, how to serialize matrix: either all values are on the same line, 
   * or all the values are in 1 column
   * @return
   */
  private String formatMatrix(List<List<String>> matrix) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int row = 0; row < matrix.size(); row++) {
      stringBuilder.append("[");
      for (int col = 0; col < matrix.get(row).size(); col++) {
        stringBuilder.append("\"");
        stringBuilder.append(matrix.get(row).get(col));
        stringBuilder.append("\"");
        if (col != matrix.get(row).size() - 1) {
          stringBuilder.append(", ");
        }
      }

      stringBuilder.append("]");
      if (row != matrix.size() - 1) {
        stringBuilder.append(",");
      }
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }
}
