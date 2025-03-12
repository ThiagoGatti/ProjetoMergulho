// mask.js
document.addEventListener("DOMContentLoaded", function () {
    const loadScript = (src, callback) => {
        if (!document.querySelector(`script[src="${src}"]`)) {
            let script = document.createElement("script");
            script.src = src;
            script.onload = callback;
            document.head.appendChild(script);
        } else {
            callback();
        }
    };

    loadScript("https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js", function () {
        loadScript("https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js", applyMasks);
    });

    function applyMasks() {
        // Aplica apenas se o elemento existir na página
        $('#numeroCelular').length && $('#numeroCelular').mask('(00) 00000-0000');
        $('#cpf').length && $('#cpf').mask('000.000.000-00');
        $('#cep').length && $('#cep').mask('00000-000');
    }
});