/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author esraa
 */
@Embeddable
public class AdminAuthorityPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "admin_email")
    private String adminEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "authority")
    private String authority;

    public AdminAuthorityPK() {
    }

    public AdminAuthorityPK(String adminEmail, String authority) {
        this.adminEmail = adminEmail;
        this.authority = authority;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminEmail != null ? adminEmail.hashCode() : 0);
        hash += (authority != null ? authority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminAuthorityPK)) {
            return false;
        }
        AdminAuthorityPK other = (AdminAuthorityPK) object;
        if ((this.adminEmail == null && other.adminEmail != null) || (this.adminEmail != null && !this.adminEmail.equals(other.adminEmail))) {
            return false;
        }
        if ((this.authority == null && other.authority != null) || (this.authority != null && !this.authority.equals(other.authority))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AdminAuthorityPK[ adminEmail=" + adminEmail + ", authority=" + authority + " ]";
    }
    
}
