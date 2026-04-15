document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password })
    });

    if (response.ok) {
        // Guardamos las credenciales para poder hacer peticiones protegidas después (Basic Auth)
        const authHeader = 'Basic ' + btoa(email + ':' + password);
        sessionStorage.setItem('auth', authHeader);

        alert("Login correcto");
        // window.location.href = 'dashboard.html'; // Descomenta cuando crees esta página
    } else {
        alert("Credenciales incorrectas");
    }
});