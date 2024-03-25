package com.github.gurinmd.assessment.game.config;

import java.io.IOException;

import com.github.gurinmd.assessment.game.config.data.Configuration;

/**
 * Reads a configuration from the path provided
 */
public interface ConfigurationReader {
  Configuration readGameConfiguration(String path) throws IOException;
}
