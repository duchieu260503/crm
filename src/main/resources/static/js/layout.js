// layout.js
document.addEventListener('DOMContentLoaded', () => {
    // console.log('[DOM Ready] layout.js script started.');

    const currentPath = window.location.pathname;
    // console.log(`[Current Path] ${currentPath}`);

    const body = document.body;

    // === Highlight Active Navigation Link ===
    const navLinks = document.querySelectorAll('.load-page-link');
    // console.log(`[NavLinks] Found ${navLinks.length} .load-page-link elements`);

    function setActiveNavLink(path) {
        // console.log(`[Highlighting] Active nav link for path: ${path}`);

        navLinks.forEach(link => {
            const page = link.getAttribute('data-page');
            const span = link.querySelector('span');

            const isActive = path.startsWith(page);
            // console.log(`  > Checking link with data-page="${page}" → ${isActive ? 'ACTIVE' : 'inactive'}`);

            link.classList.toggle('bg-viettel-red', isActive);

            if (span) {
                span.classList.toggle('text-white', isActive);
                span.classList.toggle('text-gray-800', !isActive);
            }
        });
    }

    setActiveNavLink(currentPath);

    // === Logout Modal ===
    const logoutButton = document.getElementById('logoutButton');
    const confirmLogoutButton = document.getElementById('confirmLogout');
    const cancelLogoutButton = document.getElementById('cancelLogout');
    const modal = document.getElementById('logoutConfirmationModal');

    if (logoutButton && modal && confirmLogoutButton && cancelLogoutButton) {
        // console.log('[Logout Modal] Elements found and event listeners attached.');

        logoutButton.addEventListener('click', e => {
            e.preventDefault();
            // console.log('[Logout Modal] Logout button clicked → showing modal');

            modal.classList.remove('hidden', 'block');
            modal.classList.add('flex');
            body.classList.add('overflow-hidden');
        });

        confirmLogoutButton.addEventListener('click', () => {
            // console.log('[Logout Modal] User confirmed logout → redirecting...');
            window.location.href = '/logout';
        });

        cancelLogoutButton.addEventListener('click', () => {
            // console.log('[Logout Modal] User canceled logout');
            modal.classList.remove('flex');
            modal.classList.add('hidden');
            body.classList.remove('overflow-hidden');
        });

        window.addEventListener('click', e => {
            if (e.target === modal) {
                console.log('[Logout Modal] Clicked outside modal → hiding');
                modal.classList.remove('flex');
                modal.classList.add('hidden');
                body.classList.remove('overflow-hidden');
            }
        });
    } else {
        console.warn('[Logout Modal] Some modal elements are missing.');
    }

    // === User Dropdown ===
    const dropdownButton = document.getElementById('userDropdownButton');
    const dropdownMenu = document.getElementById('userDropdown');

    if (dropdownButton && dropdownMenu) {
        // console.log('[User Dropdown] Elements found, setting up listener');

        document.addEventListener('click', e => {
            const clickedButton = dropdownButton.contains(e.target);
            const clickedInsideMenu = dropdownMenu.contains(e.target);

            if (clickedButton) {
                // console.log('[User Dropdown] Button clicked → toggling menu');
                dropdownMenu.classList.toggle('hidden');
            } else if (!clickedInsideMenu) {
                // console.log('[User Dropdown] Clicked outside menu → hiding menu');
                dropdownMenu.classList.add('hidden');
            }
        });
    }
});
