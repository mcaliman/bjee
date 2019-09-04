package dev.caliman.bjee.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users_access")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersAccess.findAll", query = "SELECT u FROM UsersAccess u")
    , @NamedQuery(name = "UsersAccess.findByUsername", query = "SELECT u FROM UsersAccess u WHERE u.username = :username")
    , @NamedQuery(name = "UsersAccess.findByPassword", query = "SELECT u FROM UsersAccess u WHERE u.password = :password")
    , @NamedQuery(name = "UsersAccess.findByGroupName", query = "SELECT u FROM UsersAccess u WHERE u.groupName = :groupName")})
public class UsersAccess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "username")
    @Id
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "group_name")
    private String groupName;

    public UsersAccess() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
}
