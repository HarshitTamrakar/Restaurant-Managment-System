package com.harshit.momentsRestro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "menuItems")
public class MenuItem {

    @Id
    private long id;
    private String name;
    private String description;
    private long rate;
}
