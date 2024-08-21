document.addEventListener('DOMContentLoaded', () => {
    // Fade-in effect for the container
    const container = document.querySelector('.container');
    if (container) {
        container.style.opacity = 0;
        setTimeout(() => {
            container.style.transition = 'opacity 1s ease-in';
            container.style.opacity = 1;
        }, 100);
    }

    // Determine if the user is logged in
    const isLoggedIn = document.body.getAttribute('data-is-logged-in') === 'true';

    // Show/hide buttons based on login state
    const authButtons = document.querySelector('.buttons.auth');
    const mainButtons = document.querySelector('.buttons.main');

    if (authButtons) {
        authButtons.style.display = isLoggedIn ? 'none' : 'block';
    }

    if (mainButtons) {
        mainButtons.style.display = isLoggedIn ? 'block' : 'none';
    }
});
