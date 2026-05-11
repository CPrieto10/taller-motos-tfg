// 1. Variables de estado y sesión
let editando = false;
const auth = sessionStorage.getItem('auth');
const motoId = sessionStorage.getItem('motoSeleccionadaId');

if (!auth || !motoId) { window.location.href = 'login.html'; }

// 2. Cargar los datos en la tabla
async function cargarMantenimientos() {
    const response = await fetch(`/api/mantenimientos/moto/${motoId}`, {
        headers: { 'Authorization': auth }
    });
    if (response.ok) {
        const mantenimientos = await response.json();
        const tbody = document.querySelector('#tablaMantenimientos tbody');
        tbody.innerHTML = '';
        mantenimientos.forEach(m => {
            tbody.innerHTML += `
                <tr>
                    <td>${m.fecha}</td>
                    <td><span class="tag">${m.tipo}</span></td>
                    <td>${m.kilometros} km</td>
                    <td>${m.descripcion || '-'}</td>
                    <td>
                        <button onclick="prepararEdicion(${m.id})" style="background:#2196F3; color:white; border:none; padding:5px; border-radius:3px; cursor:pointer;">Editar</button>
                        <button onclick="eliminarMantenimiento(${m.id})" style="background:#f44336; color:white; border:none; padding:5px; border-radius:3px; cursor:pointer;">Borrar</button>
                    </td>
                </tr>`;
        });
    }
}

// 3. Función para cuando pulsas el botón "Editar"
async function prepararEdicion(id) {
    const response = await fetch(`/api/mantenimientos/${id}`, {
        headers: { 'Authorization': auth }
    });
    if (response.ok) {
        const m = await response.json();
        // Rellenamos el formulario con lo que viene de la DB
        document.getElementById('mantenimientoId').value = m.id;
        document.getElementById('fecha').value = m.fecha;
        document.getElementById('tipoMantenimiento').value = m.tipo;
        document.getElementById('kmMantenimiento').value = m.kilometros;
        document.getElementById('descripcion').value = m.descripcion;

        editando = true;
        document.getElementById('btnGuardar').innerText = "Actualizar Registro";
        document.getElementById('btnGuardar').style.backgroundColor = "#ff9800";
    }
}

// 4. Guardar (Crear nuevo o Actualizar)
document.getElementById('mantenimientoForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const idMantenimiento = document.getElementById('mantenimientoId').value;
    const nuevo = {
        fecha: document.getElementById('fecha').value,
        tipo: document.getElementById('tipoMantenimiento').value,
        kilometros: parseInt(document.getElementById('kmMantenimiento').value),
        descripcion: document.getElementById('descripcion').value
    };

    // Si editando es true, usamos PUT. Si no, POST.
    const url = editando ? `/api/mantenimientos/${idMantenimiento}` : `/api/mantenimientos/moto/${motoId}`;
    const metodo = editando ? 'PUT' : 'POST';

    const response = await fetch(url, {
        method: metodo,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': auth
        },
        body: JSON.stringify(nuevo)
    });

    if (response.ok) {
        alert(editando ? "¡Actualizado con éxito!" : "¡Registrado con éxito!");
        resetearFormulario();
        cargarMantenimientos();
    } else {
        alert("Error al guardar. Revisa que todos los campos estén llenos.");
    }
});

function resetearFormulario() {
    editando = false;
    document.getElementById('mantenimientoForm').reset();
    document.getElementById('mantenimientoId').value = '';
    document.getElementById('btnGuardar').innerText = "Guardar Registro";
    document.getElementById('btnGuardar').style.backgroundColor = "";
}

async function eliminarMantenimiento(id) {
    if(confirm("¿Borrar este registro?")) {
        await fetch(`/api/mantenimientos/${id}`, { method: 'DELETE', headers: { 'Authorization': auth } });
        cargarMantenimientos();
    }
}

cargarMantenimientos();