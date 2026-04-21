const auth = sessionStorage.getItem('auth');
const userId = sessionStorage.getItem('userId');

if (!auth || !userId) {
    window.location.href = 'login.html';
}

// Función para listar las motos del usuario logueado
async function cargarMotos() {
    const response = await fetch(`/api/motos/usuario/${userId}`, {
        headers: { 'Authorization': auth }
    });

    if (response.ok) {
        const motos = await response.json();
        const tbody = document.querySelector('#tablaMotos tbody');
        tbody.innerHTML = '';

        motos.forEach(moto => {
            tbody.innerHTML += `
                <tr>
                    <td>${moto.marca}</td>
                    <td>${moto.modelo}</td>
                    <td>${moto.anio}</td>
                    <td>${moto.kmActuales}</td>
                    <td>
                        <button onclick="verHistorial(${moto.id})">Mantenimientos</button>
                    </td>
                </tr>`;
        });
    }
}

// Lógica para añadir una moto nueva
document.getElementById('motoForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const auth = sessionStorage.getItem('auth');
    const userId = sessionStorage.getItem('userId');

    const nuevaMoto = {
        marca: document.getElementById('marca').value,
        modelo: document.getElementById('modelo').value,
        anio: parseInt(document.getElementById('anio').value),
        // AÑADE ESTA LÍNEA
        cilindrada: parseInt(document.getElementById('cilindrada').value),
        kmActuales: parseInt(document.getElementById('kmActuales').value)
    };

    const response = await fetch(`/api/motos/usuario/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
        body: JSON.stringify(nuevaMoto)
    });

    if (response.ok) {
        alert("Moto registrada correctamente");
        document.getElementById('motoForm').reset();
        cargarMotos();
    } else {
        alert("Error al registrar. Revisa la consola del backend.");
    }
});
function verHistorial(motoId) {
    // Guardamos el ID de la moto seleccionada para que la página de mantenimientos sepa cuál es
    sessionStorage.setItem('motoSeleccionadaId', motoId);
    window.location.href = 'mantenimientos.html';
}
function cerrarSesion() {
    sessionStorage.clear();
    window.location.href = 'login.html';
}

cargarMotos();