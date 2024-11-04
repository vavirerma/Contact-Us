package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private int id;
    private String fullName;
    private String email;
    private String message;
    private boolean isArchived;
}
