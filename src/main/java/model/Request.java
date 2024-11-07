package model;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class Request {
    private int id;
    private String fullName;
    private String email;
    private String message;
    private boolean isArchived;
    private Timestamp createdAt;
}