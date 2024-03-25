package com.github.gurinmd.assessment.input;

/**
 * Command line input encapsulation
 */
public class Input {
  private Integer bettingAmount;
  private String filePath;

  public Integer getBettingAmount() {
    return bettingAmount;
  }

  public void setBettingAmount(Integer bettingAmount) {
    this.bettingAmount = bettingAmount;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}
