package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(username));
        if (!userOptional.isPresent()) {
            return false; // Người dùng không tồn tại (lỗi logic, nên không xảy ra nếu đã xác thực)
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false; // Mật khẩu cũ không đúng
        }

        // Mã hóa mật khẩu mới và cập nhật
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
        return true;
    }
}
