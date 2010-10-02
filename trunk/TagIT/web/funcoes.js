/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validarUsuario()
{
    var email = document.getElementById("email").value;
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var senha = document.getElementById("senha").value;
    var confSenha = document.getElementById("confirmacao").value;

    var erro = true;

    if ( !validaEmail(email) ){
        document.getElementById("email").focus();
        erro = false;
    }
    if ( !validaNome(nome) ){
        document.getElementById("nome").focus();
        erro = false;
    }
    if ( !validaCPF(cpf) ){
        document.getElementById("cpf").focus();
        erro = false;
    }
    if ( !validaSenha(senha, confSenha) ){
        document.getElementById("senha").value = "";
        document.getElementById("confirmacao").value = "";
        erro = false;
    }

    return erro;
}

function validaEmail(email) {
    if ( email == "" ){
        alert("Informe seu e-mail");
        return false;
    }

    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||
        (email.search(checkend) == -1)){
        alert("e-mail inválido");
        return false;
    } else {
        return true;
    }
}

function validaNome(nome){
    var expNome = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( nome == "" ){
        alert("Informe seu nome");
        return false;
    }
    if(nome.match(expNome) == null) {
        alert("Informe um nome correto");
        return false;
    }
    return true;
}

function validaRua(rua){
    var expRua = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( rua == "" ){
        alert("Informe a rua");
        return false;
    }
    if(rua.match(expRua) == null) {
        alert("Informe a rua corretamente");
        return false;
    }
    return true;
}

function validaCidade(cidade){
    var expCidade = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( cidade == "" ){
        alert("Informe a cidade");
        return false;
    }
    if(cidade.match(expCidade) == null) {
        alert("Informe a cidade corretamente");
        return false;
    }
    return true;
}

function validaCPF(cpf) {
    if ( cpf == "" ){
        alert("Informe seu CPF");
        return false;
    }

    while (cpf.lenght < 11) {
        cpf = "0" + cpf;
    }

    var nonNumbers = /\D/;
    if (nonNumbers.test(cpf)) {
        alert("Digite apenas números");
        return false;
    }

    if (cpf == "00000000000" || cpf == "11111111111" ||
        cpf == "22222222222" || cpf == "33333333333" ||
        cpf == "44444444444" || cpf == "55555555555" ||
        cpf == "66666666666" || cpf == "77777777777" ||
        cpf == "88888888888" || cpf == "99999999999"){
        alert("CPF inválido");
        return false;
    }

    var a = [];
    var b = new Number;
    var c = 11;
    for (i=0; i<11; i++){
        a[i] = cpf.charAt(i);
        if (i < 9) b += (a[i] * --c);
    }
    if ((x = b % 11) < 2) {
        a[9] = 0
    } else {
        a[9] = 11-x
    }
    b = 0;
    c = 11;
    for (y=0; y<10; y++) b += (a[y] * c--);
    if ((x = b % 11) < 2) {
        a[10] = 0;
    } else {
        a[10] = 11-x;
    }
    if ((cpf.charAt(9) != a[9]) || (cpf.charAt(10) != a[10])){
        alert("CPF inválido");
        return false;
    }
    
    return true;
}

function validaSenha(senha, confSenha){
    if ( senha == "" ){
        alert("Informe sua senha");
        return false;
    }
    if ( senha.toString().length < 6 ){
        alert("Sua senha deve ter no mínimo 6 dígitos");
        return false;
    }

    if ( senha != confSenha ){
        alert("As senhas informadas devem ser iguais");
        return false;
    }

    return true;
}

function ltrim(texto) {
    return texto.replace(/^[ ]+/, "");
}

function rtrim(texto) {
    return texto.replace(/[ ]+$/, "");
}

function trim(texto) {
    return ltrim(rtrim(texto));
}

function validaNumero(numero) {
    var expNumero = /^[0-9]*$/;
    if(numero == "") {
        alert("Informe um número");
        return false;
    }
    if(numero.match(expNumero) == null) {
        alert("Informe um número correto");
        return false;
    }
    return true;
}

function validaData(data) {
    var expData = /^([0-9]|[0,1,2][0-9]|3[0,1])\/([0,1][0,1,2])\/\d{4}$/
    if(data == "") {
        alert("Preencha a data");
        return false;
    }
    if(data.match(expData) == null) {
        alert("Preencha a data corretamente");
        return false
    }
    return true;
}

function validaContato(contato) {
    var expContato = /^[0-9a-zA-Zà-üÀ-Ü .,-@]*$/;

    if(contato == "") {
        alert("Informe um contato");
        return false;
    }
    if(contato.match(expContato) == null) {
        alert("Informe um contato correto");
        return false;
    }
    return true;
}

function validaEvento() {
    var nome = trim(document.getElementById("nome").value);
    var vagasPrincipal = trim(document.getElementById("vagasPrincipal").value);
    var vagasEspera = trim(document.getElementById("vagasEspera").value);
    var inscInicio = trim(document.getElementById("inscInicio").value);
    var inscTermino = trim(document.getElementById("inscTermino").value);
    var rua = trim(document.getElementById("rua").value);
    var numeroRua = trim(document.getElementById("numeroRua").value);
    var cidade = trim(document.getElementById("cidade").value);
    var dataEvento = trim(document.getElementById("dataEvento").value);
    var contato = trim(document.getElementById("contato").value);
    var categoria = trim(document.getElementById("categoria").value);

    var erro = false;

    if(!validaNome(nome)) {
        erro = true;
        document.getElementById("nome").focus();
    }
    if(!validaNumero(vagasPrincipal)) {
        erro = true;
        document.getElementById("vagasPrincipal").focus();
    }
    if(!validaNumero(vagasEspera)) {
        erro = true;
        document.getElementById("vagasEspera").focus();
    }
    if(!validaRua(rua)) {
        erro = true;
        document.getElementById("rua").focus();
    }
    if(!validaNumero(numeroRua)) {
        erro = true;
        document.getElementById("numeroRua").focus();
    }
    if(!validaCidade(cidade)) {
        erro = true;
        document.getElementById("cidade").focus();
    }
    if(!validaContato(contato)) {
        erro = true;
        document.getElementById("contato").focus();
    }
    if(!validaData(inscInicio)) {
        erro = true;
        document.getElementById("inscInicio").focus();
    }
    if(!validaData(inscTermino)) {
        erro = true;
        document.getElementById("inscTermino").focus();
    }
    if(!validaData(dataEvento)) {
        erro = true;
        document.getElementById("dataEvento").focus();
    }

    return !erro;
    
}

function adicionaCategoria() {
    var formEvento = document.getElementById("formEvento");
    var categoriasSelecionadas = document.getElementById("categoriasSelecionadas");
    var comboCategoria = document.getElementById("selectCategoria");
    var categoria = comboCategoria.options[comboCategoria.selectedIndex].text;
    var filhosAtuais = categoriasSelecionadas.childNodes;
    var jaExiste = false;

    for(i = 0; i < filhosAtuais.length; i++) {
        if(filhosAtuais.item(i).value == comboCategoria.value) {
            jaExiste = true;
        }
    }

    if(!jaExiste) {

        //cria o elemento XML filho do select
        var filhoCategoria = document.createElement("option");
        filhoCategoria.appendChild(document.createTextNode(categoria));
        filhoCategoria.value = comboCategoria.value;

        categoriasSelecionadas.appendChild(filhoCategoria);

        //cria o elemento XML input para adicionar os selecionados e pegar na servlet
        var filhoForm = document.createElement("input");
        filhoForm.type = "hidden";
        filhoForm.name = "categoria";
        filhoForm.value = categoria;

        formEvento.appendChild(filhoForm);
    }
    else {
        alert("Categoria já adicionada");
    }
}

function removeCategoria() {
    var formEvento = document.getElementById("formEvento");
    var categorias = document.getElementsByTagName("input");
    var categoriasSelecionadas = document.getElementById("categoriasSelecionadas");
    var categoria = categoriasSelecionadas.options[categoriasSelecionadas.selectedIndex].text
    var filhosCategorias = categoriasSelecionadas.childNodes;
    var atual = null;

    for(i = 0; i < categorias.length; i++) {
        atual = categorias[i];
        if(atual.name == "categoria") {
            if(atual.value == categoria) {
                formEvento.removeChild(atual);
            }
        }
    }

    for(i = 0; i < filhosCategorias.length; i++) {
        atual = filhosCategorias.item(i);
        if(atual.value == categoriasSelecionadas.value) {
            categoriasSelecionadas.removeChild(atual);
        }
    }

}