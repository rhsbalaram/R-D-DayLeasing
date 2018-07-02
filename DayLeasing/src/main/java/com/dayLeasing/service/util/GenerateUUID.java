package com.dayLeasing.service.util;

import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateUUID.
 *
 * @author Balaram
 */
public class GenerateUUID {

  /**
   * Generate UUID.
   *
   * @return the string
   */
  public static String generateUUID() {
    UUID randomUUID = UUID.randomUUID();
    return randomUUID.toString();

  }

}
