package com.limo.customlistview;

public class DataModel {
  private RowType rowType;
  private String name;
  private String email;
  private String mobile;

  public DataModel() {
  }

  public DataModel(RowType rowType, String name, String email, String mobile) {
    this.rowType = rowType;
    this.name = name;
    this.email = email;
    this.mobile = mobile;
  }

  public RowType getRowType() {
    return rowType;
  }

  public void setRowType(RowType rowType) {
    this.rowType = rowType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Override public String toString() {
    return "DataModel{"
        + "rowType="
        + rowType
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", mobile='"
        + mobile
        + '\''
        + '}';
  }
}
