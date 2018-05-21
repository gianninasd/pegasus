package dg.pegasus;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import dg.pegasus.validator.EmailConstraint;
import dg.pegasus.validator.UserStatusConstraint;

/**
 * User domain object
 * @author gianninasd
 */
@Entity
@Table(name="Users")
public class User implements Serializable {
  
  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  @Column(name="username")
  @Pattern(regexp="[a-z0-9-.)()]{2,50}",message="{user.username.invalid}")
  private String userName;

  @NotBlank(message="{user.name.invalid}")
  @Size(max=50,message="{user.name.invalid}")
  private String name;

  @EmailConstraint(message="{user.email.invalid}")
  private String email;

  @Column(name="status_code")
  @UserStatusConstraint(message="{user.status.invalid}")
  private String status;
  private String createdBy;
  private Date creationDate;
  private String modifiedBy;
  private Date modificationDate;

  private String password;
  private String passwordSalt;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Date getModificationDate() {
    return modificationDate;
  }

  public void setModificationDate(Date modificationDate) {
    this.modificationDate = modificationDate;
  }

  @JsonProperty(access=Access.WRITE_ONLY)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonProperty(access=Access.WRITE_ONLY)
  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }
}