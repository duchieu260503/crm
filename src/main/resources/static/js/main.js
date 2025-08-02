// Hàm tính toán thành tiền
function calculateTotal() {
    const unitPrice = parseFloat(document.getElementById('expectedRevenuePerMonth').value);
    const month = parseFloat(document.getElementById('expectedSeatsPerMonth').value);
    const totalAmount = document.getElementById('expectedValue');

    // Nếu báo giá và số tháng có giá trị hợp lệ thì tính thành tiền
    if (!isNaN(unitPrice) && !isNaN(month)) {
        totalAmount.value = (unitPrice * month)
    } else {
        totalAmount.value = '';
    }
}

const progressInput = document.getElementById('generalProgressInput');
const hiddenProgressField = document.getElementById('generalProgress');

progressInput.addEventListener('input', function () {
    const value = parseInt(progressInput.value);
    if (!isNaN(value) && value >= 0 && value <= 100) {
        hiddenProgressField.value = value + "%";
    } else {
        hiddenProgressField.value = "";
    }
});

document.addEventListener('DOMContentLoaded', () => {
    const hiddenInput = document.getElementById('generalProgress');
    const visibleInput = document.getElementById('generalProgressInput');

    // Extract the numeric part from stored "75%" string
    if (hiddenInput.value) {
        const percent = parseInt(hiddenInput.value.replace('%', '')) || 0;
        visibleInput.value = percent;
    }

    // Update hidden field whenever visible input changes
    visibleInput.addEventListener('input', () => {
        hiddenInput.value = visibleInput.value ? visibleInput.value + '%' : '';
    });
});

// Hàm tính toán hiệu lực đến ngày
function calculateContractEndDate() {
    const startDateInput = document.getElementById('startDate');
    const monthsInput = document.getElementById('durationInMonths');
    const endDateInput = document.getElementById('endDate');

    const startDateStr = startDateInput.value;
    const months = parseInt(monthsInput.value);

    if (startDateStr && !isNaN(months)) {
        const date = new Date(startDateStr);
        date.setMonth(date.getMonth() + months);

        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');

        endDateInput.value = `${year}-${month}-${day}`;
    } else {
        endDateInput.value = '';
    }
}

// Gọi các hàm khi DOM đã được tải hoàn tất để tính toán giá trị ban đầu (nếu có)
document.addEventListener('DOMContentLoaded', () => {
    calculateTotal(); // Tính toán thành tiền ban đầu
    document.getElementById('expectedRevenuePerMonth').addEventListener('input', calculateTotal);
    document.getElementById('expectedSeatsPerMonth').addEventListener('input', calculateTotal);
    calculateContractDuration(); // Tính toán hiệu lực đến ngày ban đầu
});


document.getElementById('signedDate').addEventListener('change', calculateContractDuration);
document.getElementById('month').addEventListener('input', calculateContractDuration);


function calculateVatAndTotal() {
    const base = parseFloat(document.getElementById('contractValueNoVat').value) || 0;
    const vat = +(base * 0.1)
    const total = +(base + vat)

    document.getElementById('vat').value = vat;
    document.getElementById('totalValue').value = total;
}