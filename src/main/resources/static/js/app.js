// Esta funciÃ³n se ejecuta al mostrarse el modal y lo que hace es actualizar
// la URL del elemento "<a>"
$(document).ready(function() {
	$('#delete-modal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget);
		var id = button.data('id');
		var modal = $(this);
		var a = modal.find('.modal-body a')[0];
		//a.href = a.href + data;
		a.href = $(a).data('ruta') + id;
	});
});