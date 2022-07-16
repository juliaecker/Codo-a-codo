<div class="modal fade" id="agregarAutoModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Auto</h5>
            </div>
            <form action="${pageContext.request.contextPath}/servletControlador?accion=insertar" method="POST" class="was-validated">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Marca</label>
                        <input type="text" class="form-control" name="marca" required="">
                    </div>
                    <div class="form-group">
                        <label for="autor">Modelo</label>
                        <input type="text" class="form-control" name="modelo" required="">
                    </div>
                    <div class="form-group">
                        <label for="cantPaginas">Año</label>
                        <input type="number" class="form-control" name="año" required="">
                    </div>
                    <div class="form-group">
                        <label for="precio">Precio</label>
                        <input type="currency" class="form-control" name="precio" required="">
                    </div>
                    <div class="form-group">
                        <label for="copias">Stock</label>
                        <input type="number" class="form-control" name="stock" required="">
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">
                        Guardar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
