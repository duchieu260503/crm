package pl.coderslab;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangePasswordResponse {
    private boolean success;
    private String message;

    public ChangePasswordResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
