// script.js
document.addEventListener('DOMContentLoaded', function() {
    const selectElement = document.getElementById('roles');
    if (selectElement) {
        // Lắng nghe sự kiện click trên toàn bộ select box
        selectElement.addEventListener('mousedown', function(event) {
            if (event.target.tagName === 'OPTION') {
                event.preventDefault(); // Ngăn chặn hành vi mặc định của trình duyệt
                const clickedOption = event.target;

                // Đảm bảo rằng phần tử được click là một <option>
                // if (clickedOption.tagName === 'OPTION') {
                // Đảo ngược trạng thái 'selected' của option đó
                clickedOption.selected = !clickedOption.selected;
                selectElement.focus(); // Quan trọng: Giữ focus trên thẻ select
            }
            // }
        });

        // Để gửi các giá trị đã chọn khi form được submit
        // Các giá trị đã chọn sẽ tự động được gửi khi form được submit
        // vì chúng ta đang thay đổi thuộc tính .selected của các option.
    }
});