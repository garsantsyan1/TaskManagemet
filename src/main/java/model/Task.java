package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    private int id;
    private String name;
    private String description;
    private Status status;
    private Date deadline;
    private int userID;
    private boolean isExpired;
}
