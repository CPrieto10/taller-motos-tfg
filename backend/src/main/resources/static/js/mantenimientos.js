const auth = sessionStorage.getItem('auth');
const motoId = sessionStorage.getItem('motoSeleccionadaId');

if (!auth || !motoId) {
    window.location.href = 'dashboard.html';
}

// 1. Cargar el historial al entrar
async function cargarMantenimientos() {
    const response = await fetch(`/api/mantenimientos/moto/${motoId}`, {
        headers: { 'Authorization': auth }
    });

    if (response.ok) {
        const mantenimientos = await response.json();
        const tbody = document.querySelector('#tablaMantenimientos tbody');
        tbody.innerHTML = '';

        mantenimientos.sort((a, b) => new Date(b.fecha) - new Date(a.fecha)); // Ordenar por fecha reciente

        mantenimientos.forEach(m => {
            tbody.innerHTML += `
                <tr>
                    <td>${m.fecha}</td>
                    <td><span class="tag">${m.tipo}</span></td>
                    <td>${m.kilometros} km</td>
                    <td>${m.descripcion || '-'}</td>
                    <td>
                        <button onclick="eliminarMantenimiento(${m.id})" style="background: #444; padding: 5px 10px; font-size: 0.8rem;">Borrar</button>
                    </td>
                </tr>`;
        });
    }
}

// 2. Guardar nuevo mantenimiento
document.getElementById('mantenimientoForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const nuevo = {
        fecha: document.getElementById('fecha').value,
        tipo: document.getElementById('tipoMantenimiento').value,
        // CAMBIA 'km' POR 'kilometros' PARA QUE COINCIDA CON EL BACKEND
        kilometros: parseInt(document.getElementById('kmMantenimiento').value),
        descripcion: document.getElementById('descripcion').value
    };

    const response = await fetch(`/api/mantenimientos/moto/${motoId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': sessionStorage.getItem('auth')
        },
        body: JSON.stringify(nuevo)
    });

    if (response.ok) {
        alert("Mantenimiento registrado con éxito");
        document.getElementById('mantenimientoForm').reset();
        cargarMantenimientos(); // Recarga la tabla
    } else {
        const error = await response.json();
        alert("Error: " + (error.message || "No se pudo guardar"));
    }
});
async function eliminarMantenimiento(id) {
    if(confirm("¿Seguro que quieres borrar este registro?")) {
        const response = await fetch(`/api/mantenimientos/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': auth }
        });
        if (response.ok) cargarMantenimientos();
    }
}

function cerrarSesion() {
    sessionStorage.clear();
    window.location.href = 'login.html';
}

cargarMantenimientos();