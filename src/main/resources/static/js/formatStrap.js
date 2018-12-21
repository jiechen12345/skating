function showCross(id) {
    var tmp = id + "Pic"
    var image = document.getElementById(tmp);
    image.src = "/img/cross_icon.png";
    image.width = 20;
    image.height = 20;
    image.style = "";
}

function showCheck(id) {
    var tmp = id + "Pic"
    var image = document.getElementById(tmp);
    image.src = "/img/icon-check.png";
    image.width = 20;
    image.height = 20;
    image.style = "";
}

function isName(id) {
    var name_element = document.getElementById(id);
    var name = name_element.value;

    if (letterOnly(name) && name.length < 30 && name.length > 0) {
        name_element.setAttribute('passCode', 1);
        showCheck(id);
    } else {
        name_element.setAttribute('passCode', 0);
        showCross(id);
    }
}

function isAccount(id) {

    var account_element = document.getElementById(id);
    var account = account_element.value;

    if (letterOnly(account) && account.length < 12 && account.length > 5) {
        account_element.setAttribute('passCode', 1);
        showCheck(id);
    } else {
        account_element.setAttribute('passCode', 0);
        showCross(id);
    }
}

function isPassword(id) {
    var password_element = document.getElementById("newPassword");
    var password = password_element.value;
    if (letterOnly(password) && password.length < 12 && password.length > 5) {
        password_element.setAttribute('passCode', 1);
        showCheck(id);
    } else {
        password_element.setAttribute('passCode', 0);
        showCross(id);
    }
}

function checkPassword(id) {
    var password1_element = document.getElementById("newPassword");
    var password1 = password1_element.value;
    var password2_element = document.getElementById(id);
    var password2 = password2_element.value;

    if (password1 == password2) {
        password2_element.setAttribute('passCode', 1);
        showCheck(id);
    } else {
        password2_element.setAttribute('passCode', 0);
        showCross(id);
    }
}

function letterOnly(data) {
    //only accept a~z A~Z 0~9 _ and -
    for (var i = 0; i < data.length; i++) {
        if (!data[i].match("[a-zA-Z0-9_-]")) {
            return false;
        }
    }
    return true;
}

function engOnly(data) {
    //only accept a~z and A~Z
    for (var i = 0; i < data.length; i++) {
        if (!data[i].match("[a-zA-Z]")) {
            return false;
        }
    }
    return true;
}

function allSubmit() {
    var checkName = document.getElementById('newName');
    var cName = checkName.getAttribute('passCode');
    var checkAccount = document.getElementById('newAccount');
    var cAccount = checkAccount.getAttribute('passCode');
    var checkPassword = document.getElementById('newPassword');
    var cPassword = checkPassword.getAttribute('passCode');
    var checkPassword2 = document.getElementById('cPassword');
    var cPassword2 = checkPassword2.getAttribute('passCode');

    // if($("#newName").getAttribute('passCode')&&$("#newAccount").getAttribute('passCode')&&
    //     $("#newPassword").getAttribute('passCode')&&$("#cPassword").getAttribute('passCode')){

    if (cName && cAccount != 0 && cPassword && cPassword2) {
            console.log(checkAccount.value);
            $("#myForm").submit();

    } else {
        alertWarning("請輸入完整資料");
    }
}