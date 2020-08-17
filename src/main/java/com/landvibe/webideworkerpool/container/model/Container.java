package com.landvibe.webideworkerpool.container.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "containers")
public class Container {
    private String id;
    private String name;
    private String description;
    private String status;
    private String type;
    private String createdAt;
    private String updatedAt;
    private String userId;
}
