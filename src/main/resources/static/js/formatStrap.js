function isName(id) {
    var name_element = document.getElementById(id);
    var name = name_element.value;
    document.getElementById("nameCheck").style.display = "none";
    if (name != "aaa01") {
        document.getElementById("nameCross").style = "";
    } else {
        document.getElementById("nameCross").style.display = "none";
        document.getElementById("nameCheck").style = "";
    }
}

function isAccount(id) {
    var account_element = document.getElementById(id);
    var account = account_element.value;
    //should be correct formation
    document.getElementById("accountCheck").style.display = "none";
    if (account != "aaa01") {
        document.getElementById("accountCross").style = "";
    } else {
        document.getElementById("accountCross").style.display = "none";
        document.getElementById("accountCheck").style = "";
    }
}

function isPassword(id) {
    var password_element = document.getElementById("newPassword");
    var password = password_element.value;
    //should be correct formation and length
    document.getElementById("passCheck").style.display = "none";
    if (password != "123") {
        document.getElementById("passCross").style = "";
    } else {
        document.getElementById("passCross").style.display = "none";
        document.getElementById("passCheck").style = "";
    }
}

function checkPassword(id) {
    var password1_element = document.getElementById("newPassword");
    var password1 = password1_element.value;
    var password2_element = document.getElementById("cfmPassword");
    var password2 = password2_element.value;

    document.getElementById("pass2Check").style.display = "none";
    if (password1 != password2) {
        document.getElementById("pass2Cross").style = "";
    } else {
        document.getElementById("pass2Cross").style.display = "none";
        document.getElementById("pass2Check").style = "";
    }
}
