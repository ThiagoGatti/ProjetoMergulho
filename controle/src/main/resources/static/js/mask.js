document.addEventListener("DOMContentLoaded", function () {
    const loadScript = (src, callback) => {
        let script = document.createElement("script");
        script.src = src;
        script.onload = callback;
        document.head.appendChild(script);
    };

    // Carregar jQuery e jQuery Mask antes de aplicar as máscaras
    loadScript("https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js", function () {
        loadScript("https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js", function () {
            applyMasks();
        });
    });

    function applyMasks() {
        $('#numeroCelular').mask('(00) 00000-0000');
        $('#telefone').mask('(00) 0000-0000');
        $('#cpf').mask('000.000.000-00');
        $('#rg').mask('00.000.000-0');
        $('#cep').mask('00000-000');
    }
});
