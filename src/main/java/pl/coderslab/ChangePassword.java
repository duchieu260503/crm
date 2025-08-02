package pl.coderslab;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePassword {
    private String oldPassword;
    private String newPassword;

}
