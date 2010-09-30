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
    if ( nome == "" ){
        alert("Informe seu nome");
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