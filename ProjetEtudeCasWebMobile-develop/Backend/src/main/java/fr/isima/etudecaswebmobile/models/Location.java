package fr.isima.etudecaswebmobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private String label;
    private double latitude;
    private double longitude;
    private List<Tag> tags;

}
