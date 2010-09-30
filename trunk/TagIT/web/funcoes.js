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
}

function validaCPF(cpf) {
    var erro = "";
    if (cpf.length < 11) erro += "Sao necessarios 11 digitos" +
    "para verificacao do CPF! \n\n";
    var nonNumbers = /\D/;
    if (nonNumbers.test(cpf)) erro += "A verificacao de CPF" +
    "suporta apenas numeros! \n\n";
    if (cpf == "00000000000" || cpf == "11111111111" ||
        cpf == "22222222222" || cpf == "33333333333" ||
        cpf == "44444444444" || cpf == "55555555555" ||
        cpf == "66666666666" || cpf == "77777777777" ||
        cpf == "88888888888" || cpf == "99999999999"){
        erro += "Numero de CPF invalido!"
    }
    var a = [];
    var b;
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
        erro +="Digito verificador com problema!";
    }
    if (erro.length > 0){
        alert(erro);
        return false;
    }
    return true;
}