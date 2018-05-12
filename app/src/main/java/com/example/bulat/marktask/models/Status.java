package com.example.bulat.marktask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

  @SerializedName("error")
  @Expose
  private String mError;

  public String getError() {
    return mError;
  }
}
