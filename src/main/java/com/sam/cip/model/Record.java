package com.sam.cip.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "client_policy", indexes = {
        @Index(name = "UK_CLIENT_POLICY", columnList = "clientID, policyID", unique = true)
})
@XmlRootElement(name = "record")
@JsonPropertyOrder({ "clientID", "policyID", "insuredAmount", "monthlyPremium", "discount", "description"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Record implements Serializable {

    private static final long serialVersionUID = -4360614379450148631L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @NotNull
    @XmlAttribute(name = "clientID")
    @Column(name = "clientID", nullable = false)
    @JsonProperty(value = "clientID")
    private long clientID;

    @NotNull
    @XmlAttribute(name = "policyID")
    @Column(name = "policyID", nullable = false)
    @JsonProperty(value = "policyID")
    private String policyID;

    @NotNull
    @XmlElement(name = "insuredAmount")
    @Column(name = "insured_amount", nullable = false)
    @JsonProperty(value = "insuredAmount")
    private double insuredAmount;

    @NotNull
    @XmlElement(name = "monthlyPremium")
    @Column(name = "monthly_premium", nullable = false)
    @JsonProperty(value = "monthlyPremium")
    private double monthlyPremium;

    @NotNull
    @XmlElement(name = "discount")
    @Column(name = "discount", nullable = false)
    @JsonProperty(value = "discount")
    private float discount;

    @NotNull
    @XmlElement(name = "description")
    @Column(name = "description", nullable = false)
    @JsonProperty(value = "description")
    private String description;

    public Record() {
    }

    public Record(@NotNull long clientID, @NotNull String policyId, @NotNull double insuredAmount,
                  @NotNull double monthlyPremium, @NotNull float discount, @NotNull String description) {
        this.clientID = clientID;
        this.policyID = policyId;
        this.insuredAmount = insuredAmount;
        this.monthlyPremium = monthlyPremium;
        this.discount = discount;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", clientID=" + clientID +
                ", policyID='" + policyID + '\'' +
                ", insuredAmount=" + insuredAmount +
                ", monthlyPremium=" + monthlyPremium +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                '}';
    }

    public long getClientID() {
        return clientID;
    }

    public String getPolicyID() {
        return policyID;
    }

    public double getInsuredAmount() {
        return insuredAmount;
    }

    public double getMonthlyPremium() {
        return monthlyPremium;
    }

    public float getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }
}
