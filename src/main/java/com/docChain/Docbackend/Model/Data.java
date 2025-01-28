package com.docChain.Docbackend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@lombok.Data
@Table(name = "data")
public class Data {
    @Id
    @Column(name = "AadharNumber")
    String aadharNumber;
    @Column(name = "Name")
    String Name;
    @Column(name = "Gender")
    String Gender;
    @Column(name = "DoB")
    String DoB;
}
