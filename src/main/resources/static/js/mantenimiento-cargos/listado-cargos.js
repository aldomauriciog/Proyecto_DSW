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
		headerName: "Codigo",
		field: "idCargo",
		width: 80
	},
	{
		headerName: "Nombre Cargo",
		field: "nombre"
	},
	{
		headerName: "Nombre Area",
		field: "tbArea",
		valueGetter(params) {
			return params.data.tbArea ? params.data.tbArea.nombre : null;
		}
	},
	{
		headerName: "Sueldo",
		field: "sueldo"
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
	const resp = await fetch("http://localhost:8080/api/tbcargos/findAll", { method: "GET" })
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

	if (rowNode) {
		await llenarSelectEmpleados(rowNode);
		document.querySelector("[name=idCargo]").value = rowNode.idCargo;
		document.querySelector("[name=nombre]").value = rowNode.nombre;
		document.querySelector("[name=idArea]").value = rowNode.tbArea.idArea;
		document.querySelector("[name=sueldo]").value = rowNode.sueldo;
	} else {
		await llenarSelectEmpleados(rowNode);
	}
}

const limpiarModal = () => {
	console.log("limpiarModal")
	document.querySelector("[name=idCargo]").value = "";
	document.querySelector("[name=idArea]").value = "";
	document.querySelector("[name=nombre]").value = "";
	document.querySelector("[name=sueldo]").value = "";
}

const eliminar = async (rowNode) => {
	Swal.fire({
		title: '¿Desea eliminar el area ' + rowNode.nombre + '?',
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
			const response = await fetch("http://localhost:8080/api/tbcargos", {
				method: "DELETE",
				body: JSON.stringify({ idCargo: rowNode.idCargo }),
				headers: myHeaders,
			  	redirect: 'follow'
			})
			Swal.close();
			toastr.success("Area eliminada correctamente");
			listadoUsuarios();
		}
	});
}

const guardar = async () => {

	const body = {
		nombre: document.querySelector("[name=nombre]").value,
		idCargo: parseInt(document.querySelector("[name=idCargo]").value),
		sueldo: parseFloat(document.querySelector("[name=sueldo]").value),
		tbArea: {
			idArea: parseInt(document.querySelector("[name=idArea]").value)
		}
	};
	let myHeaders = new Headers();
	myHeaders.append("Content-Type", "application/json");
	if (document.querySelector("[name=idCargo]").value!="") {
		const response = await fetch(`http://localhost:8080/api/tbcargos`,
			{
				method: "PUT",
				body: JSON.stringify(body),
				headers: myHeaders,
			  	redirect: 'follow'
			}).then(res => res.json());
		if (!response.error) {
			toastr.success("Registro modificado correctamente");
		} else {
			toastr.error(response.data);
		}
	} else {
		const response = await fetch(`http://localhost:8080/api/tbcargos`,
			{
				method: "POST",
				body: JSON.stringify(body),
				headers: myHeaders,
			  	redirect: 'follow'
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
	
	let options = "";
	if (rowNode) {
		const empleados = await fetch("http://localhost:8080/api/tbareas/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		empleados.forEach(e => {
			options += e.idArea == rowNode.tbArea.idArea
				? `<option selected value=${e.idArea}>${e.nombre}</option>`
				: `<option value=${e.idArea}>${e.nombre}</option>`;
		})
	} else {
		const empleados = await fetch("http://localhost:8080/api/tbareas/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		empleados.forEach(e => {
			options += `<option value=${e.idArea}>${e.nombre}</option>`;
		})
	}

	document.querySelector("[name=idArea]").innerHTML = options;
}

window.addEventListener("load", init);