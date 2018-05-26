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
@Table(name = "admin_authority")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminAuthority.findAll", query = "SELECT a FROM AdminAuthority a")
    , @NamedQuery(name = "AdminAuthority.findByAdminEmail", query = "SELECT a FROM AdminAuthority a WHERE a.adminAuthorityPK.adminEmail = :adminEmail")
    , @NamedQuery(name = "AdminAuthority.findByAuthority", query = "SELECT a FROM AdminAuthority a WHERE a.adminAuthorityPK.authority = :authority")})
public class AdminAuthority implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AdminAuthorityPK adminAuthorityPK;
    @JoinColumn(name = "admin_email", referencedColumnName = "email", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Admin admin;

    public AdminAuthority() {
    }

    public AdminAuthority(AdminAuthorityPK adminAuthorityPK) {
        this.adminAuthorityPK = adminAuthorityPK;
    }

    public AdminAuthority(String adminEmail, String authority) {
        this.adminAuthorityPK = new AdminAuthorityPK(adminEmail, authority);
    }

    public AdminAuthorityPK getAdminAuthorityPK() {
        return adminAuthorityPK;
    }

    public void setAdminAuthorityPK(AdminAuthorityPK adminAuthorityPK) {
        this.adminAuthorityPK = adminAuthorityPK;
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
        hash += (adminAuthorityPK != null ? adminAuthorityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminAuthority)) {
            return false;
        }
        AdminAuthority other = (AdminAuthority) object;
        if ((this.adminAuthorityPK == null && other.adminAuthorityPK != null) || (this.adminAuthorityPK != null && !this.adminAuthorityPK.equals(other.adminAuthorityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AdminAuthority[ adminAuthorityPK=" + adminAuthorityPK + " ]";
    }
    
}
