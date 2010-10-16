/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validarCadastroUsuario()
{
    var email = document.getElementById("email").value;
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var senha = document.getElementById("senha").value;
    var confSenha = document.getElementById("confirmacao").value;

    var message = "";
    message += validaEmail(email);
    message += validaNome(nome);
    message += validaCPF(cpf);
    message += validaSenha(senha, confSenha);
    if(message != ""){
        message += "<p>- Clique na caixa para fechá-la.</p>";
        mostrarMenssagem('critical', message);
        return false;
    }
    return true;
}


function validarAlteracaoUsuario()
{
    var nome = document.getElementById("nome").value;
    var cpf = document.getElementById("cpf").value;
    var atual = document.getElementById("atual").value;
    var senha = document.getElementById("senha").value;
    var confSenha = document.getElementById("confirmacao").value;

    
    var message = "";
    message += validaNome(nome);
    message += validaCPF(cpf);
    if ( atual == "" ){
        message += "<p>- Informe a <strong>senha</strong>.</p>";
    } else {
        if ( senha != "" || confSenha != "" ){
            message += validaSenha(senha, confSenha);
            }
        }
    if(message != ""){
        message += "<p>- Clique na caixa para fechá-la.</p>";
        mostrarMenssagem('critical', message);
        return false;
    }
    return true;
}

function validaEmail(email) {
    if ( email == "" ){
        return "<p>- Informe o <strong>e-mail</strong>.</p>";
    }

    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||
        (email.search(checkend) == -1)){
        return "<p>- <strong>e-mail</strong> com formato incorreto.</p>";
    } else {
        return "";
    }
}

function validaNome(nome){
    var expNome = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( nome == "" ){
        return "<p>- Informe o <strong>nome</strong>.</p>";
    }
    if(nome.match(expNome) == null) {
        return "<p>- <strong>nome</strong> com formato incorreto.</p>";
    }
    return "";
}

function validaRua(rua){
    var expRua = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( rua == "" ){
        return "<p>- Informe a <strong>rua</strong>.</p>";
    }
    if(rua.match(expRua) == null) {
        return "<p>- <strong>rua</strong> com formato incorreto.</p>";
    }
    return "";
}

function validaCidade(cidade){
    var expCidade = /^[0-9a-zA-Zà-üÀ-Ü /.]*$/
    if ( cidade == "" ){
        return "<p>- Informe a <strong>cidade</strong>.</p>";
    }
    if(cidade.match(expCidade) == null) {
        return "<p>- <strong>cidade</strong> com formato incorreto.</p>";
    }
    return "";
}

function validaCPF(cpf) {
    if ( cpf == "" ){
        return "<p>- Informe o <strong>CPF</strong>.</p>";
    }

    while (cpf.lenght < 11) {
        cpf = "0" + cpf;
    }

    var nonNumbers = /\D/;
    if (nonNumbers.test(cpf)) {
        return "<p>- <strong>CPF</strong> deve conter apenas números.</p>";
    }

    if (cpf == "00000000000" || cpf == "11111111111" ||
        cpf == "22222222222" || cpf == "33333333333" ||
        cpf == "44444444444" || cpf == "55555555555" ||
        cpf == "66666666666" || cpf == "77777777777" ||
        cpf == "88888888888" || cpf == "99999999999"){
        return "<p>- <strong>CPF</strong> inválido.</p>";
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
        return "<p>- <strong>CPF</strong> inválido.</p>";
    }
    
    return "";
}

function validaSenha(senha, confSenha){
    if ( senha == "" ){
        return "<p>- Informe a <strong>senha</strong>.</p>";
    }
    if ( senha.toString().length < 6 ){
        return "<p>- Informe a <strong>senha</strong> com no mínimo<strong>6 dígitos</dtong.</p>";
    }

    if ( senha != confSenha ){
        return "<p>- <strong>senha</strong> e <strong>confirmação de senha</strong> devem ser iguais.</p>";
    }

    return "";
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
        return "<p>- Informe um <strong>número</strong>.</p>";
    }
    if(numero.match(expNumero) == null) {
        return "<p>- <strong>número</strong> com formato incorreto.</p>";
    }
    return "";
}

function validaData(data) {
    var expData = /^([0-9]|[0,1,2][0-9]|3[0,1])\/([0,1][0,1,2])\/\d{4}$/
    if(data == "") {
        return "<p>- Informe a <strong>data</strong>.</p>";;
    }
    if(data.match(expData) == null) {
        return "<p>- <strong>data</strong> com formato incorreto.</p>";
    }
    return "";
}

function validaContato(contato) {
    var expContato = /^[0-9a-zA-Zà-üÀ-Ü .,-@]*$/;

    if(contato == "") {
        return "<p>- Informe um <strong>contato</strong>.</p>";
    }
    if(contato.match(expContato) == null) {
        return "<p>- <strong>contato</strong> com formato incorreto.</p>";
    }
    return "";
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

    var message = "";
    message += validaNome(nome);
    message += validaNumero(vagasPrincipal);
    message += validaNumero(vagasEspera);
    message += validaRua(rua);
    message += validaNumero(numeroRua);
    message += validaCidade(cidade);
    message += validaContato(contato);
    message += validaData(inscInicio)
    message += validaData(inscTermino);
    message += validaData(dataEvento);

    if(message != ""){
        message += "<p>- Clique na caixa para fechá-la.</p>";
        mostrarMenssagem('critical', message);
        return false;
    }

    return true;
    
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

function validarLogin(){
    var email = document.getElementById("email").value;
    var senha = document.getElementById("senha").value;
    var erro = true;
       alert(erro);
    if (validaEmail(email) != ""){
        erro = false;
        
        document.getElementById("email");
    }
    
    if (!validaSenha(senha, senha) != ""){
        erro = false;
        
        document.getElementById("senha").focus();
    }
    
    return erro;

}

function fecharCaixaMensagem(){
    if (document.getElementById("erros") != null )
        document.getElementById("erros").innerHTML="";
}

function mostrarMenssagem(type, message) {
    switch (type) {
        case 'information':
            document.getElementById("erros").innerHTML = "<fieldset class=\"information\" onclick=\"fecharCaixaMensagem()\"><legend>Informação</legend>" + message + "</fieldset>";
            break;
        case 'critical':
            document.getElementById("erros").innerHTML = "<fieldset class=\"critical\" onclick=\"fecharCaixaMensagem()\"><legend>Erro</legend>" + message + "</fieldset>";
            break;
        case 'success':
            document.getElementById("erros").innerHTML = "<fieldset class=\"success\" onclick=\"fecharCaixaMensagem()\"><legend>Successo</legend>" + message + "</fieldset>";
            break;
        case 'warning':
            document.getElementById("erros").innerHTML = "<fieldset class=\"warning\" onclick=\"fecharCaixaMensagem()\"><legend>Aviso</legend>" + message + "</fieldset>";
            break;
    }
}
