document.addEventListener('DOMContentLoaded', async function() {
    if (await hasAuthSession()) {
        window.location.href = '/home';
    }
});

document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const errorDiv = document.getElementById('errorMessage');

    try {
        console.log('-> Frontend: POST /api/auth/login username=', username);
        const response = await apiFetch('/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            skipAuthRedirect: true,
            body: JSON.stringify({ username, password })
        });
        console.log('<- Frontend: POST /api/auth/login status=' + response.status);

        if (!response.ok) {
            const errorText = await response.text();
            console.error('Frontend: POST /api/auth/login error response=', errorText);
            errorDiv.textContent = errorText;
            errorDiv.style.color = 'red'; // Manipular el DOM vía JS sí está permitido por CSP
            return;
        }

        console.log('<- Frontend: POST /api/auth/login cookie set');
        window.location.href = '/home';
    } catch (error) {
        console.error('Frontend: POST /api/auth/login error=', error);
        errorDiv.textContent = 'Error de conexión al backend.';
        errorDiv.style.color = 'red';
    }
});