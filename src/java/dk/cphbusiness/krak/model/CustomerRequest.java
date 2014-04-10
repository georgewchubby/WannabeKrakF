package dk.cphbusiness.krak.model;

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

/**
 *
 * @author joachim
 */
@Entity
@Table(name = "CUSTOMER_REQUEST")
@NamedQueries({
    @NamedQuery(name = "CustomerRequest.findAll", query = "SELECT c FROM CustomerRequest c")})
@XmlRootElement
public class CustomerRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CUSTOMER")
    private String customer;
    @Column(name = "REQUESTS")
    private Integer requests;

    public CustomerRequest() {
    }
    
    public void update() {
        requests+=1;
    }

    public CustomerRequest(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customer != null ? customer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerRequest)) {
            return false;
        }
        CustomerRequest other = (CustomerRequest) object;
        if ((this.customer == null && other.customer != null) || (this.customer != null && !this.customer.equals(other.customer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.cphbusiness.krak.model.CustomerRequest[ customer=" + customer + " ]";
    }
    
}
