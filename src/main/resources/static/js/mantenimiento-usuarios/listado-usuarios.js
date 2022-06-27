/**
 * 
 */
let gridApi;
let rowNode;
toastr.options = {
	"positionClass": "toast-bottom-right",
	"showDuration": "300",
	"hideDuration": "1000",
	"timeOut": "5000",
	"extendedTimeOut": "1000",
	"showEasing": "swing",
	"hideEasing": "linear",
	"showMethod": "fadeIn",
	"hideMethod": "fadeOut"
}
const columnDefs = [
	{
		headerName: "ID",
		field: "idUsuario",
		width: 50
	},
	{
		headerName: "Nombre Usuario",
		field: "username"
	},
	{
		headerName: "Nombres Empleado",
		field: "tbEmpleado.nombres"
	},
	{
		headerName: "Apellidos Empleado",
		valueGetter(params) {
			return `${params.data.tbEmpleado.apePaterno} ${params.data.tbEmpleado.apeMaterno}`;
		}
	},
	{
		headerName: "Acciones",
		pinned: "right",
		width: 100,
		cellRenderer: 'editDeleteComponent'
	},

];

// specify the data
const rowData = [];

// let the grid know which columns and what data to use
const gridOptions = {
	columnDefs: columnDefs,
	rowData: rowData,
	context: {
		openModal: (rowNode) => openModal(rowNode),
		delete: (rowNode) => eliminar(rowNode)
	},
	components: {
		editDeleteComponent: EditDeleteComponent,
	},
	overlayLoadingTemplate:
		'<span class="ag-overlay-loading-center"><i class="fas fa-spinner fa-pulse"></i></span>',
	overlayNoRowsTemplate:
		`<span class="ag-overlay-loading-center border-0 bg-transparent shadow-none">
  No se encontraron registros...
  </span>`,
	pagination: true,
	suppressPaginationPanel: true,
	paginationPageSize: 4
};

const listadoUsuarios = async () => {
	gridApi.showLoadingOverlay();
	const resp = await fetch("http://localhost:8080/api/tbusuarios/findAll", { method: "GET" })
		.then(res => res.json());
	gridApi.setRowData(resp.data);
}

const search = () => {
	gridApi.setQuickFilter(document.getElementById('search').value);
}

const init = async () => {
	const gridDiv = document.querySelector('#myGrid');
	new agGrid.Grid(gridDiv, gridOptions);
	gridApi = gridOptions.api;
	gridApi.setDomLayout('autoHeight');
	document.querySelector('#myGrid').style.height = '';
	await listadoUsuarios();
}
const onPageSizeChanged = () => {
	const value = document.getElementById('page-size').value;
	gridApi.paginationSetPageSize(Number(value));
}
const previous = () => {
	gridApi.paginationGoToPreviousPage();
	document.getElementById("page").innerHTML = gridOptions.api.paginationGetCurrentPage() + 1
}
const next = () => {
	gridApi.paginationGoToNextPage();
	document.getElementById("page").innerHTML = gridOptions.api.paginationGetCurrentPage() + 1
}
const openModal = async (rowNode = null) => {
	$('#exampleModal').modal('show');

	if (!rowNode) {
		document.querySelector("[name=idEmpleado]").disabled = false;
		document.querySelector("[name=nomUsuario]").disabled = false;
		await llenarSelectEmpleados(rowNode);
	} else {
		document.querySelector("[name=idEmpleado]").disabled = true;
		document.querySelector("[name=nomUsuario]").disabled = true;
	
		await llenarSelectEmpleados(rowNode);
		document.querySelector("[name=idUsuario]").value = rowNode.idUsuario;
		document.querySelector("[name=nomUsuario]").value = rowNode.username;
		document.querySelector("[name=idEmpleado]").value = rowNode.idUsuario;
	    document.getElementById("previous-img").src = rowNode.foto ? rowNode.foto : "/assets/adminLTE/img/no-user-image-icon-27.jpg";
	}
}

const limpiarModal = () => {
	document.querySelector("[name=idUsuario]").value = "";
	document.querySelector("[name=nomUsuario]").value = "";
	document.querySelector("[name=idEmpleado]").value = "";
	document.querySelector("[name=claveUsuario]").value = "";
	document.querySelector("[name=claveUsuarioConfirmacion]").value = "";
	document.querySelector('input[type="file"]').value = "";
    document.getElementById("previous-img").src = "/assets/adminLTE/img/no-user-image-icon-27.jpg";
	document.getElementById("file-name").innerHTML = "";
}

const eliminar = async (rowNode) => {
	Swal.fire({
		title: '¿Desea eliminar el usuario ' + rowNode.username + '?',
		icon: 'question',
		showConfirmButton: true,
		showCancelButton: true,
		confirmButtonText: 'SI',
		cancelButtonText: "NO",
		allowOutsideClick: false
	}).then(async (res) => {
		let myHeaders = new Headers();
		myHeaders.append("Content-Type", "application/json");
		if (res.value) {
			Swal.showLoading();
			const response = await fetch("http://localhost:8080/api/tbusuarios", {
				method: "DELETE",
				body: JSON.stringify({ idUsuario: rowNode.idUsuario }),
				headers: myHeaders,
			  	redirect: 'follow'
			})
			Swal.close();
			toastr.success("Usuario eliminado correctamente");
			listadoUsuarios();
		}
	});
}

const guardar = async () => {
	if (document.querySelector("[name=claveUsuario]").value != document.querySelector("[name=claveUsuarioConfirmacion]").value) {
		toastr.error("Las contraseñas no coinciden")
		return;
	}
	const body = {
		username: document.querySelector("[name=nomUsuario]").value,
		idUsuario: parseInt(document.querySelector("[name=idEmpleado]").value),
		clave: document.querySelector("[name=claveUsuario]").value,
	};
	var input = document.querySelector('input[type="file"]')
	console.log(input.files)
	var data = new FormData()
	data.append('file', input.files.length > 0 ? input.files[0] : null);
	data.append('usuario', new Blob([JSON.stringify(body)], {
                    type: "application/json"
                }))
	if (document.querySelector("[name=idEmpleado]").disabled) {
		const response = await fetch(`http://localhost:8080/api/tbusuarios/updateCustom`,
			{
				method: "PUT",
				body: data,
			}).then(res => res.json());
		if (!response.error) {
			toastr.success("Registro modificado correctamente");
		} else {
			toastr.error(response.data);
		}
	} else {
		const response = await fetch(`http://localhost:8080/api/tbusuarios/insertCustom`,
			{
				method: "POST",
				body: data,
			}).then(res => res.json());
		if (!response.error) {
			toastr.success("Registro insertado correctamente");
		} else {
			toastr.error(response.data);
		}
	}
	limpiarModal();
	listadoUsuarios();
	$('#exampleModal').modal('hide');
}

const llenarSelectEmpleados = async (rowNode) => {
	console.log(rowNode, "rownode")
	
	let options = "";
	if (rowNode) {
		const empleados = await fetch("http://localhost:8080/api/tbempleados/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		empleados.forEach(e => {
			options += e.idEmpleado == rowNode.idUsuario
				? `<option selected value=${e.idEmpleado}>${e.nombres} ${e.apePaterno} ${e.apeMaterno}</option>`
				: `<option value=${e.idUsuario}>${e.nombres} ${e.apePaterno} ${e.apeMaterno}</option>`;
		})
	} else {
		const empleados = await fetch("http://localhost:8080/api/tbempleados/findByTbUsuarioIsNull", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		empleados.forEach(e => {
			options += `<option value=${e.idEmpleado}>${e.nombres} ${e.apePaterno} ${e.apeMaterno}</option>`;
		})
	}

	document.querySelector("[name=idEmpleado]").innerHTML = options;
}
const onChangeImg = (e)=> {	
	const [file] = e.files
	  if (file) {
		document.getElementById("file-name").innerHTML = file.name;
	    document.getElementById("previous-img").src = URL.createObjectURL(file)
	  }	
}
window.addEventListener("load", init);