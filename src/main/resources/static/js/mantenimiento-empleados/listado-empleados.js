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
		field: "idEmpleado",
		width: 50
	},
	{
		headerName: "Nombre",
		field: "nombres"
	},
	{
		headerName: "Apellido Paterno",
		field: "apePaterno"
	},
	{
		headerName: "Apellidos Materno",
		field: "apeMaterno"
	},
	{
		headerName: "Nro. Documento",
		field: "nroDocumento"
	},
	{
		headerName: "Genero",
		field: "sexo"
	},
	{
		headerName: "Cargo",
		field: "tbCargo",
		valueGetter(params) {
			return params.data.tbCargo.nombre
		}
	},
	{
		headerName: "Sistema de Pension",
		field: "tbSistemaPension",
		valueGetter(params) {
			return params.data.tbSistemaPension.nombre
		}
	},
	{
		headerName: "Email",
		field: "email"
	},
		{
		headerName: "Celular",
		field: "celular"
	},

	{
		headerName: "Acciones",
		pinned: "right",
		width: 200,
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
		delete: (rowNode) => eliminar(rowNode),
		reporte: (rowNode) => reporte(rowNode)
	},
	components: {
		editDeleteComponent: EditDeleteReportComponent,
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
	const resp = await fetch("http://localhost:8080/api/tbempleados/findAll", { method: "GET" })
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
		document.querySelector("[name=idEmpleado]").disabled = true;
		await llenarSelectTipoDocumento(rowNode);
		await llenarSelectArea(rowNode);
		await llenarSelectTipoHorario(rowNode);
		await llenarSelectSistemaPension(rowNode);
	} else {
		document.querySelector("[name=idEmpleado]").disabled = true;
	
		await llenarSelectTipoDocumento(rowNode);
		await llenarSelectArea(rowNode);
		await llenarSelectTipoHorario(rowNode);
		await llenarSelectSistemaPension(rowNode);
		document.querySelector("[name=idEmpleado]").value = rowNode.idEmpleado;
		document.querySelector("[name=nombres]").value = rowNode.nombres;
		document.querySelector("[name=apePaterno]").value = rowNode.apePaterno;
		document.querySelector("[name=apeMaterno]").value = rowNode.apeMaterno;
		document.querySelector("[name=sexo]").value = rowNode.sexo;
		document.querySelector("[name=fechaNacimiento]").value = rowNode.fechaNacimiento;
		document.querySelector("[name=idTipoDocumento]").value = rowNode.tbTipoDocumento.idTipoDocumento;
		document.querySelector("[name=nroDocumento]").value = rowNode.nroDocumento;
		document.querySelector("[name=estadoCivil]").value = rowNode.estadoCivil;
		document.querySelector("[name=email]").value = rowNode.email;
		document.querySelector("[name=celular]").value = rowNode.celular;
		document.querySelector("[name=direccion]").value = rowNode.direccion;
		document.querySelector("[name=hijos]").value = rowNode.hijos;
		document.querySelector("[name=idArea]").value = rowNode.tbCargo.tbArea.idArea;
		document.querySelector("[name=idCargo]").value = rowNode.tbCargo.idCargo;
		document.querySelector("[name=fechaIngreso]").value = rowNode.fechaIngreso;
	}
}

const limpiarModal = () => {
	document.querySelector("[name=idEmpleado]").value = "";
	document.querySelector("[name=nombres]").value = "";
	document.querySelector("[name=apePaterno]").value = "";
	document.querySelector("[name=apeMaterno]").value = "";
	document.querySelector("[name=sexo]").value = "M";
	document.querySelector("[name=fechaNacimiento]").value = "";
	document.querySelector("[name=idTipoDocumento]").value = "";
	document.querySelector("[name=nroDocumento]").value = "";
	document.querySelector("[name=estadoCivil]").value = "S";
	document.querySelector("[name=email]").value = "";
	document.querySelector("[name=celular]").value = "";
	document.querySelector("[name=direccion]").value = "";
	document.querySelector("[name=hijos]").value = "";
	document.querySelector("[name=idArea]").value = "";
	document.querySelector("[name=idCargo]").value = "";
	document.querySelector("[name=fechaIngreso]").value = "";
}

const eliminar = async (rowNode) => {
	Swal.fire({
		title: '¿Desea eliminar el usuario ' + rowNode.nombres + " " + rowNode.apePaterno + '?',
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
			const response = await fetch("http://localhost:8080/api/tbempleados", {
				method: "DELETE",
				body: JSON.stringify({ idEmpleado: rowNode.idEmpleado }),
				headers: myHeaders,
			  	redirect: 'follow'
			})
			Swal.close();
			toastr.success("Usuario eliminado correctamente");
			listadoUsuarios();
		}
	});
}
const reporte = (data) => {
	window.open("http://localhost:8080/api/tbempleados/boleta/"+data.nroDocumento,"_blank")
}
const guardar = async () => {

	const body = {
		nombres: document.querySelector("[name=nombres]").value,
		idEmpleado: parseInt(document.querySelector("[name=idEmpleado]").value),
		apePaterno: document.querySelector("[name=apePaterno]").value,
		apeMaterno: document.querySelector("[name=apeMaterno]").value,
		sexo: document.querySelector("[name=sexo]").value,
		fechaNacimiento: document.querySelector("[name=fechaNacimiento]").value,
		tbTipoDocumento: {
			idTipoDocumento: parseInt(document.querySelector("[name=idTipoDocumento]").value)
		},
		nroDocumento: document.querySelector("[name=nroDocumento]").value,
		estadoCivil: document.querySelector("[name=estadoCivil]").value,
		email: document.querySelector("[name=email]").value,
		celular: document.querySelector("[name=celular]").value,
		direccion: document.querySelector("[name=direccion]").value,
		hijos: parseInt(document.querySelector("[name=hijos]").value),
		tbCargo: {
			idCargo: parseInt(document.querySelector("[name=idCargo]").value)
		},
		tbTipoHorario: {
			idTipoHorario: parseInt(document.querySelector("[name=idTipoHorario]").value)
		},
		tbSistemaPension: {
			idSistemaPension: parseInt(document.querySelector("[name=idSistemaPension]").value)
		},
		fechaIngreso: document.querySelector("[name=fechaIngreso]").value
	};
	let myHeaders = new Headers();
	myHeaders.append("Content-Type", "application/json");
	if (document.querySelector("[name=idEmpleado]").value != "") {
		const response = await fetch(`http://localhost:8080/api/tbempleados`,
			{
				method: "PUT",
				body: JSON.stringify(body),
				headers: myHeaders
			}).then(res => res.json());
		if (!response.error) {
			toastr.success("Registro modificado correctamente");
		} else {
			toastr.error(response.data);
		}
	} else {
		const response = await fetch(`http://localhost:8080/api/tbempleados`,
			{
				method: "POST",
				body: JSON.stringify(body),
				headers: myHeaders
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
const llenarSelectTipoDocumento = async (rowNode) => {
	let options = "";
	if (rowNode) {
		const tipoDocumentos = await fetch("http://localhost:8080/api/tbtipodocumentos/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoDocumentos.forEach(e => {
			options += e.idTipoDocumento == rowNode.tbTipoDocumento.idTipoDocumento
				? `<option selected value=${e.idTipoDocumento}>${e.nombre}</option>`
				: `<option value=${e.idTipoDocumento}>${e.nombre}</option>`;
		})
	} else {
		const tipoDocumentos = await fetch("http://localhost:8080/api/tbtipodocumentos/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoDocumentos.forEach(e => {
			options += `<option value=${e.idTipoDocumento}>${e.nombre}</option>`;
		})
	}

	document.querySelector("[name=idTipoDocumento]").innerHTML = options;
}

const llenarSelectSistemaPension = async (rowNode) => {
	let options = "";
	if (rowNode) {
		const tipoDocumentos = await fetch("http://localhost:8080/api/tbsistemapensiones/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoDocumentos.forEach(e => {
			options += e.idSistemaPension == rowNode.tbSistemaPension.idSistemaPension
				? `<option selected value=${e.idSistemaPension}>${e.nombre}</option>`
				: `<option value=${e.idSistemaPension}>${e.nombre}</option>`;
		})
	} else {
		const tipoDocumentos = await fetch("http://localhost:8080/api/tbsistemapensiones/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoDocumentos.forEach(e => {
			options += `<option value=${e.idSistemaPension}>${e.nombre}</option>`;
		})
	}

	document.querySelector("[name=idSistemaPension]").innerHTML = options;
}

const llenarSelectTipoHorario = async (rowNode) => {
	let options = "";
	if (rowNode) {
		const tipoHorarios = await fetch("http://localhost:8080/api/tbtipohorarios/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoHorarios.forEach(e => {
			options += e.idTipoHorario == rowNode.tbTipoHorario.idTipoHorario
				? `<option selected value=${e.idTipoHorario}>${e.nombre}</option>`
				: `<option value=${e.idTipoHorario}>${e.nombre}</option>`;
		})
	} else {
		const tipoHorarios = await fetch("http://localhost:8080/api/tbtipohorarios/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		tipoHorarios.forEach(e => {
			options += `<option value=${e.idTipoHorario}>${e.nombre}</option>`;
		})
	}

	document.querySelector("[name=idTipoHorario]").innerHTML = options;
}

const llenarSelectArea = async (rowNode) => {
	let options = "";
	if (rowNode) {
		const areas = await fetch("http://localhost:8080/api/tbareas/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		areas.forEach(e => {
			options += e.idArea == rowNode.tbCargo.tbArea.idArea
				? `<option selected value=${e.idArea}>${e.nombre}</option>`
				: `<option value=${e.idArea}>${e.nombre}</option>`;
		})
		
	} else {
		const areas = await fetch("http://localhost:8080/api/tbareas/findAll", { method: "GET" })
		.then(res => res.json().then(r => r.data));
		areas.forEach(e => {
			options += `<option value=${e.idArea}>${e.nombre}</option>`;
		})
	}
	document.querySelector("[name=idArea]").innerHTML = options;
	await llenarSelectCargo();

}

const llenarSelectCargo = async () => {
	let options = "";
	const idArea = document.querySelector("[name=idArea]").value;
	const areas = await fetch(`http://localhost:8080/api/tbcargos/findByIdArea/${idArea}`, { method: "GET" })
	.then(res => res.json().then(r => r.data));
	areas.forEach(e => {
		options += `<option value=${e.idCargo}>${e.nombre}</option>`;
	})

	document.querySelector("[name=idCargo]").innerHTML = options;
}

const onChangeImg = (e)=> {
	const [file] = e.files
	  if (file) {
		document.getElementById("file-name").innerHTML = file.name;
	    document.getElementById("previous-img").src = URL.createObjectURL(file)
	  }
}
window.addEventListener("load", init);