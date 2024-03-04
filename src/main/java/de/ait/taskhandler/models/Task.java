package de.ait.taskhandler.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private Long id;
    private String title;
    private String description;
    private String assignTo;
    
}
