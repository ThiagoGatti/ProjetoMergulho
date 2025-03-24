document.addEventListener("DOMContentLoaded", function() {
    // Verifica se jQuery e jQuery Mask já estão carregados
    if (typeof window.jQuery === 'undefined') {
        console.error("jQuery não está carregado!");
        return;
    }

    if (typeof $.fn.mask === 'undefined') {
        console.error("jQuery Mask não está carregado!");
        return;
    }

    // Aplica as máscaras
    $(document).ready(function() {
        $('#rg').mask('00.000.000-0', { reverse: true });
        $('#telefone').mask('0000-0000');
        $('#numeroCelular').mask('(00) 00000-0000');
        $('#cep').mask('00000-000');
        $('#cpf').mask('000.000.000-00');
    });
});