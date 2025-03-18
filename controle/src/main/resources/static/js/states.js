const statesBR = [
    "Acre", "Alagoas", "Amapá", "Amazonas",
    "Bahia", "Ceará", "Distrito Federal",
    "Espírito Santo", "Goiás", "Maranhão",
    "Mato Grosso", "Mato Grosso do Sul",
    "Minas Gerais", "Pará", "Paraíba",
    "Paraná", "Pernambuco", "Piauí",
    "Rio de Janeiro", "Rio Grande do Norte",
    "Rio Grande do Sul", "Rondônia",
    "Roraima", "Santa Catarina",
    "São Paulo", "Sergipe", "Tocantins"
];

function populateStates(selectId, currentState) { // Adicione o parâmetro
    const select = document.getElementById(selectId);
    if (!select) return;

    select.innerHTML = '<option value="" disabled selected>Selecione um estado</option>';

    statesBR.forEach(state => {
        const option = document.createElement("option");
        option.value = state;
        option.textContent = state;
        option.selected = (state === currentState); // Compare com o parâmetro
        select.appendChild(option);
    });
}

