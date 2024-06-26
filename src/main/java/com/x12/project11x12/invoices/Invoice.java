package com.x12.project11x12.invoices;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.x12.project11x12.inscriptionsParticipants.InscriptionParticipant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_invoice")
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private Set<InscriptionParticipant> inscriptionParticipant;

    private Double total;
    private Double discount;
    private Double final_total;
    private Double pay;

    public Invoice(){
    }

    public Invoice(Long id, Double total, Double discount,
            Double final_total, Double pay) {
        this.id = id;
        this.total = total;
        this.discount = discount;
        this.final_total = final_total;
        this.pay = pay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<InscriptionParticipant> getInscriptionParticipant() {
        return inscriptionParticipant;
    }

    public void setInscriptionParticipant(Set<InscriptionParticipant> inscriptionParticipant) {
        this.inscriptionParticipant = inscriptionParticipant;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinal_total() {
        return final_total;
    }

    public void setFinal_total(Double final_total) {
        this.final_total = final_total;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }







    
}
