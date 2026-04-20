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
        // 1. Leer el JSON de la respuesta para obtener el userId
        const data = await response.json();

        // 2. Guardar las credenciales (Basic Auth)
        const authHeader = 'Basic ' + btoa(email + ':' + password);
        sessionStorage.setItem('auth', authHeader);

        // 3. Guardar el userId recibido del servidor
        sessionStorage.setItem('userId', data.userId);

        alert("Login correcto");

        // 4. Activar la redirección al panel de control
        window.location.href = 'dashboard.html';
    } else {
        alert("Credenciales incorrectas");
    }
});