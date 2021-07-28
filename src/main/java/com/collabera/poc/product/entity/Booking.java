package com.collabera.poc.product.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String requestId;

    @Column(nullable = false)
    private String referenceNumber;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String message;

    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "user_id",
        foreignKey = @ForeignKey(name = "fk_user_id"),
        referencedColumnName = "id",
        nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_id"),
        referencedColumnName = "id",
        nullable = false)
    private Product product;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @CreationTimestamp
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime transactionDate;
}
