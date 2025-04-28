package org.act.ticketingsystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime startDate;   // When the ticket work should start
    private LocalDateTime dueDate;     // When the ticket work should be completed

    private String assignedTo;         // Person to whom the ticket is assigned
    private String assignedBy;         // Person who assigned the ticket
}
