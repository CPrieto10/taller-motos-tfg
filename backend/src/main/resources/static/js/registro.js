document.getElementById('registroForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const usuario = {
        nombre: document.getElementById('nombre').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        fechaAlta: new Date().toISOString().split('T')[0] // Formato YYYY-MM-DD
    };

    const response = await fetch('/api/usuarios', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    });

    if (response.ok) {
        alert("¡Cuenta creada! Ahora loguéate.");
        window.location.href = 'login.html'; // Te manda al login automáticamente
    } else {
        const error = await response.json();
        alert("Error al registrarse: " + (error.message || "Revisa los datos"));
    }
});