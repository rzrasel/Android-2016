package com.limo.customlistview;

public enum RowType {
  TITLE("title"), DETAILS("details");

  private final String rowType;

  RowType(String rowType) {
    this.rowType = rowType;
  }

  public String getRowType() {
    return rowType;
  }
}
