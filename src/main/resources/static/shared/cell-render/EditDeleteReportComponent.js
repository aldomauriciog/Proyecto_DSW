 class EditDeleteReportComponent {

    params;

    init(params) {
        this.params = params;

        this.gui = document.createElement("div");
        this.gui.classList.add("row");
        this.gui.classList.add("justify-content-center");
        this.gui.innerHTML = `<div role='button' id="editButton"  data-toggle="tooltip" data-placement="top" title="Editar" class='botonEditar col-auto text-warning'><i class='fas fa-edit'></i></div>
        <div role='button' data-toggle="tooltip" data-placement="top" title="Eliminar" class='botonEliminar col-auto text-danger'><i class="fas fa-trash-alt"></i></div>
        <div role='button' data-toggle="tooltip" data-placement="top" title="Reporte Boleta" class='botonEliminar col-auto text-danger'><i class="fa fa-file"></i></div>`;
        setTimeout(()=> {
			const editButtons = document.getElementsByClassName("botonEditar");
			for (let e of editButtons) {
                e.setAttribute("data-bs-toggle","modal");	
                e.setAttribute("data-bs-target","#exampleModal");
            }
        },150);
        this.gui.firstChild.addEventListener("click", ()=>this.edit());
        this.gui.children[1].addEventListener("click", ()=>this.delete());
        this.gui.lastChild.addEventListener("click", ()=>this.reporte());
    }

    edit() {
        this.params.context.openModal(this.params.data);
    }
    delete() {
        this.params.context.delete(this.params.data)
    }
    reporte() {
        this.params.context.reporte(this.params.data)
    }

    getGui() {

        return this.gui;
    }
}
