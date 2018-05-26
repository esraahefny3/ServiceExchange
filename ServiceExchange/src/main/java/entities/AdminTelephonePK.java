/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
public class AdminTelephonePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "admin_email")
    private String adminEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "telephone")
    private String telephone;

    public AdminTelephonePK() {
    }

    public AdminTelephonePK(String adminEmail, String telephone) {
        this.adminEmail = adminEmail;
        this.telephone = telephone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminEmail != null ? adminEmail.hashCode() : 0);
        hash += (telephone != null ? telephone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminTelephonePK)) {
            return false;
        }
        AdminTelephonePK other = (AdminTelephonePK) object;
        if ((this.adminEmail == null && other.adminEmail != null) || (this.adminEmail != null && !this.adminEmail.equals(other.adminEmail))) {
            return false;
        }
        if ((this.telephone == null && other.telephone != null) || (this.telephone != null && !this.telephone.equals(other.telephone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AdminTelephonePK[ adminEmail=" + adminEmail + ", telephone=" + telephone + " ]";
    }
    
}
