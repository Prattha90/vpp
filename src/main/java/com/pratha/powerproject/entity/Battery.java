package com.pratha.powerproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * The type Battery.
 *
 * @author Pramosh Shrestha
 * @created 01 /11/2023: 17:43
 */
@Getter
@Setter
@Accessors(chain = true)
@Table(
        name = "battery",
        uniqueConstraints = {@UniqueConstraint(name = "battery_name_uk", columnNames = {"name"})}
)
@Entity
public class Battery {

    @Id
    @SequenceGenerator(name = "battery_id_seq_gen", sequenceName = "battery_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "battery_id_seq_gen")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    @Column(name = "capacity", nullable = false)
    @Positive
    private Integer capacity;
}
