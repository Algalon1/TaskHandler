package de.ait.taskhandler.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Task {

    public enum Status{
        DRAFT, TODO, IN_PROGRESS, TESTING, DONE
    }

    public enum Priority{
        LOWEST, LOW, MEDIUM, HIGH, HIGHEST
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, length = 100)
    private String assignTo;

    @Enumerated(value = EnumType.STRING)
    @Check(constraints = "status in ('DRAFT', 'TODO', 'IN_PROGRESS', 'TESTING', 'DONE')")
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Check(constraints = "priority in ('LOWEST', 'LOW', 'MEDIUM', 'HIGH', 'HIGHEST')")
    private Priority priority;
    
}
