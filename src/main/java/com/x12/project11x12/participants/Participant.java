package com.x12.project11x12.participants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.x12.project11x12.inscriptions.InscriptionParticipant;
import com.x12.project11x12.profiles.Profile;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participant")
    private Long id;

    @Column(name = "participant_name")
    private String participantName;

    @Column(name = "participant_surname")
    private String participantSurname;

    @Column(name = "DNI")
    private String dni;

    @Column(name = "street")
    private String street;

    @Column(name = "PC")
    private String pc;

    @Column(name = "city")
    private String city;

    @Column(name = "municipality")
    private String municipality;

    @Column(name = "mail")
    private String mail;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "alergies")
    private String allergies;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    private Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "participant")
    private Set<InscriptionParticipant> inscriptions;

    public Participant() {
    }

    public Participant(String participantName, String participantSurname, String dni, String street, String pc, String city, String municipality, String mail, Date birthDate, String allergies, String remarks) {
        this.participantName = participantName;
        this.participantSurname = participantSurname;
        this.dni = dni;
        this.street = street;
        this.pc = pc;
        this.city = city;
        this.municipality = municipality;
        this.mail = mail;
        this.birthDate = birthDate;
        this.allergies = allergies;
        this.remarks = remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getParticipantSurname() {
        return participantSurname;
    }

    public void setParticipantSurname(String participantSurname) {
        this.participantSurname = participantSurname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<InscriptionParticipant> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Set<InscriptionParticipant> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
