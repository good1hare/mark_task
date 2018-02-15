package com.example.bulat.marktask.utils;

public class ConstantsManager {
  public final static String G_PLUS_SCOPE = "oauth2:https://www.googleapis.com/auth/plus.me";
  public final static String USERINFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
  public final static String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  public final static String SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;
}
