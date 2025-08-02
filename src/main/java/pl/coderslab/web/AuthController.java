package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.ChangePassword;
import pl.coderslab.ChangePasswordResponse;
import pl.coderslab.service.AuthService;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/change-password-form") // Endpoint để hiển thị form
    public String changePasswordForm() {
        return "change_password"; // Trả về file change_password.html
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(Principal principal, @RequestBody ChangePassword changePasswordRequest) {
        String username = principal.getName(); // Lấy username của người dùng đã xác thực
        boolean success = authService.changePassword(username, changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword());
        if (success) {
            return ResponseEntity.ok(new ChangePasswordResponse(true, "Đổi mật khẩu thành công!"));
        } else {
            return ResponseEntity.badRequest().body(new ChangePasswordResponse(false, "Mật khẩu cũ không đúng."));
        }
    }
}
