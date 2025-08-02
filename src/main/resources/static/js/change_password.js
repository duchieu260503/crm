document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('changePasswordForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const oldPassword = document.getElementById('oldPassword').value;
        const newPassword = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (newPassword !== confirmPassword) {
            messageDiv.textContent = 'Mật khẩu mới và xác nhận mật khẩu không khớp.';
            messageDiv.className = 'message error';
            return;
        }

        const formData = {
            oldPassword: oldPassword,
            newPassword: newPassword
        };

        fetch('/api/auth/change-password', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => response.json())
            .then(data => {
                messageDiv.textContent = data.message;
                messageDiv.className = data.success ? 'message success' : 'message error';
                form.reset(); // Xóa form sau khi thành công
            })
            .catch(error => {
                console.error('Lỗi:', error);
                messageDiv.textContent = 'Đã có lỗi xảy ra khi đổi mật khẩu.';
                messageDiv.className = 'message error';
            });
    });
});