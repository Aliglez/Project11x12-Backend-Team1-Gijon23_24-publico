package com.x12.project11x12.inscriptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.x12.project11x12.inscriptionsParticipants.InscriptionsParticipants;
import com.x12.project11x12.schools.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscriptions")
public class Inscriptions {

    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscription")
    private Long id;

    @Column
    private Integer school_id;
    
    @Column
    private Date inscription_date;

    @Column
    private Integer week_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_schools")
    private School school;

/*     @OneToOne
    @JoinColumn(name = "id_week") */
    // ***** ERIKA ****    private CampWeeks campWeeks;

    @OneToMany(mappedBy = "inscriptions", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InscriptionsParticipants> inscriptionsParticipants = new ArrayList<>();


    public Inscriptions() {
    }

    public Inscriptions(Long id, Integer school_id, Date inscription_date, Integer week_id,
            List<InscriptionsParticipants> inscriptionsParticipants) {
        this.id = id;
        this.school_id = school_id;
        this.inscription_date = inscription_date;
        this.week_id = week_id;
        this.inscriptionsParticipants = inscriptionsParticipants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public Date getInscription_date() {
        return inscription_date;
    }

    public void setInscription_date(Date inscription_date) {
        this.inscription_date = inscription_date;
    }

    public Integer getWeek_id() {
        return week_id;
    }

    public void setWeek_id(Integer week_id) {
        this.week_id = week_id;
    }

    public List<InscriptionsParticipants> getInscriptionsParticipants() {
        return inscriptionsParticipants;
    }

    public void setInscriptionsParticipants(List<InscriptionsParticipants> inscriptionsParticipants) {
        this.inscriptionsParticipants = inscriptionsParticipants;
    }

    
    
/*     @OneToMany(mappedBy = "inscriptions", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InscriptionsParticipants> inscriptionsParticipants = new HashSet<>(); */

/*     @OneToMany(mappedBy = "inscriptions")
    //@JoinColumn(name = "id_ins_part")
    private Set<InscriptionsParticipants> inscriptionsParticipants; */

    /* @ManyToMany
    @JoinTable(
        name = "inscriptions_participants",
        joinColumns = @JoinColumn(name = "inscription_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<InscriptionsParticipants> participants; */


 
}
