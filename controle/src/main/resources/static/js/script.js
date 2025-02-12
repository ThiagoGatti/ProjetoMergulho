function openEditDialog(button) {
    $('#editId').val($(button).data('id'));
    $('#editNome').val($(button).data('nome'));
    $('#editEmail').val($(button).data('email'));
    $('#editDataNascimento').val($(button).data('datanascimento'));
    $('#editNumeroCelular').val($(button).data('numerocelular'));
    $('#editCep').val($(button).data)('cep'));

    $('#editUserForm').attr('action', '/users/update/' + $(button).data('id'));
    $('#editUserModal').show();
  }

  function closeEditDialog() {
    $('#editUserModal').hide();
  }