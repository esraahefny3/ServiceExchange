/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author esraa
 */
@Entity
@Table(name = "admin_telephone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminTelephone.findAll", query = "SELECT a FROM AdminTelephone a")
    , @NamedQuery(name = "AdminTelephone.findByAdminEmail", query = "SELECT a FROM AdminTelephone a WHERE a.adminTelephonePK.adminEmail = :adminEmail")
    , @NamedQuery(name = "AdminTelephone.findByTelephone", query = "SELECT a FROM AdminTelephone a WHERE a.adminTelephonePK.telephone = :telephone")})
public class AdminTelephone implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminTelephonePK adminTelephonePK;
    @JoinColumn(name = "admin_email", referencedColumnName = "email", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Admin admin;

    public AdminTelephone() {
    }

    public AdminTelephone(AdminTelephonePK adminTelephonePK) {
        this.adminTelephonePK = adminTelephonePK;
    }

    public AdminTelephone(String adminEmail, String telephone) {
        this.adminTelephonePK = new AdminTelephonePK(adminEmail, telephone);
    }

    public AdminTelephonePK getAdminTelephonePK() {
        return adminTelephonePK;
    }

    public void setAdminTelephonePK(AdminTelephonePK adminTelephonePK) {
        this.adminTelephonePK = adminTelephonePK;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminTelephonePK != null ? adminTelephonePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminTelephone)) {
            return false;
        }
        AdminTelephone other = (AdminTelephone) object;
        if ((this.adminTelephonePK == null && other.adminTelephonePK != null) || (this.adminTelephonePK != null && !this.adminTelephonePK.equals(other.adminTelephonePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AdminTelephone[ adminTelephonePK=" + adminTelephonePK + " ]";
    }
    
}
