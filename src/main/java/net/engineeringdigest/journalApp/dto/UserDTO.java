package net.engineeringdigest.journalApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotEmpty
    private String userName;

    private String email;
    private boolean sentimentAnalysis;

    @NotEmpty
    private String password;
}
