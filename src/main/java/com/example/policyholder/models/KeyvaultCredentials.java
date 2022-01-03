package com.example.policyholder.models;

import java.io.Serializable;
import java.util.Map;

public class KeyvaultCredentials implements Serializable {

  private static final long serialVersionUID = 1L;

  private String _id;
  private String tenantId;
  private Map<String, String> keyvaultCredentials;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public Map<String, String> getKeyvaultCredentials() {
    return keyvaultCredentials;
  }

  public void setKeyvaultCredentials(Map<String, String> keyvaultCredentials) {
    this.keyvaultCredentials = keyvaultCredentials;
  }
}
