package com.flatmate.resolver.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintType complaintType;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    private LocalDateTime timestamp;

    private boolean resolved = false;

    private int upvotes = 0;

    private int downvotes = 0;

    @ManyToOne
    @JoinColumn(name = "flat_id", nullable = false)
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;
}
