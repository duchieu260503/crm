document.addEventListener('DOMContentLoaded', function () {
    const showLoginModalBtn = document.getElementById('showLoginModalBtn');
    const loginModal = document.getElementById('loginModal');
    const closeLoginModalBtn = document.getElementById('closeLoginModalBtn');
    const body = document.body; // Reference to the body element

    // Function to open modal and lock scroll
    function openLoginModal() {
        loginModal.classList.remove('hidden');
        body.classList.add('overflow-hidden'); // Lock scroll
    }

    // Function to close modal and unlock scroll
    function closeLoginModal() {
        loginModal.classList.add('hidden');
        body.classList.remove('overflow-hidden'); // Unlock scroll
    }

    // Show login modal
    if (showLoginModalBtn) {
        showLoginModalBtn.addEventListener('click', openLoginModal);
    }

    // Hide login modal
    if (closeLoginModalBtn) {
        closeLoginModalBtn.addEventListener('click', closeLoginModal);
    }

    // Hide modal if clicked outside
    loginModal.addEventListener('click', function (event) {
        if (event.target === loginModal) {
            closeLoginModal();
        }
    });
});
