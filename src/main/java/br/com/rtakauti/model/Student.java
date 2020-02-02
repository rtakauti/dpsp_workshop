package br.com.rtakauti.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Student extends AbstractEntity{

    private String name;
    private double longitude;
    private double latitude;
}
