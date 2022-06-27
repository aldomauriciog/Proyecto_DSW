

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

setInterval(()=> {
	const date = new Date;
	const hora = date.getHours().toString().padStart(2,"0");
	const minutos = date.getMinutes().toString().padStart(2,"0");
	const segundos = date.getSeconds().toString().padStart(2,"0");
	const horaActual = `${hora}:${minutos}:${segundos}`
	document.querySelector("#hora").innerHTML = horaActual;
},1000)

const columnDefs = [
	{
		headerName: "Fecha",
		field: "fecha"
	},
	{
		headerName: "Hora Ingreso",
		field: "horaIngreso"
	},
	{
		headerName: "Hora Salida",
		field: "horaSalida"
	},
	{
		headerName: "Observación",
		field: "observacion"
	}
]

const gridOptions = {
	columnDefs: columnDefs,
  	rowData: [{
		fecha: "test",
		horaIngreso: "test",
		horaSalida: "TEST",
		observacion: "AAAA"
	}],
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

let gridApi;

const init = async () => {
	const gridDiv = document.querySelector("#myGrid");
	new agGrid.Grid(gridDiv, gridOptions);
	gridApi = gridOptions.api;
	gridApi.setDomLayout('autoHeight');
    document.querySelector('#myGrid').style.height = '';
    gridApi.showLoadingOverlay();
    await listarMarcacion();
}

const listarMarcacion = async () => {
	const response = await fetch("/api/tbmarcaciones/findAll", {method: "GET"})
						.then(res=>res.json());
	gridApi.setRowData(response.data);
}
const search = () => {
  gridApi.setQuickFilter(document.getElementById('search').value);
}
const openModal = () => {
	  $('#exampleModal').modal('show');
}

const limpiarModal = () => {
	document.querySelector("[name='observacion']").value="";
}
const onPageSizeChanged = () => {
  const value = document.getElementById('page-size').value;
  gridApi.paginationSetPageSize(Number(value));
}
const previous = () => {
  gridApi.paginationGoToPreviousPage();
  document.getElementById("page").innerHTML=gridOptions.api.paginationGetCurrentPage() + 1
}
const next = () => {
  gridApi.paginationGoToNextPage();
  document.getElementById("page").innerHTML=gridOptions.api.paginationGetCurrentPage() + 1
}

const guardar = async () => {
	let myHeaders = new Headers();
	myHeaders.append("Content-Type", "application/json");
	await fetch("/api/tbmarcaciones/registrar", 
			{
				method: "POST",
				body: JSON.stringify({
					observacion: document.querySelector("[name='observacion']").value
				}),
				headers: myHeaders
			}).then(async (res)=> {
				const response = await res.json();
				if(!response.error) {
					toastr.success(response.data);
				} else {
					toastr.error(response.data);
				}
			});
  $('#exampleModal').modal('hide');
  gridApi.showLoadingOverlay();
    await listarMarcacion();
}

window.addEventListener("load", init);