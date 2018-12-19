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
    if (letterOnly(name) && name.length < 30) {
        showCheck(id);
    } else {
        showCross(id);
    }
}

function isAccount(id) {

    var account_element = document.getElementById(id);
    var account = account_element.value;


    if (letterOnly(account) && account.length < 12 && account.length > 5) {
        showCheck(id);
    } else {
        showCross(id);
    }
}

function isPassword(id) {
    var password_element = document.getElementById("newPassword");
    var password = password_element.value;
    if (letterOnly(password) && password.length < 12 && password.length > 5) {
        showCheck(id);
    } else {
        showCross(id);
    }
}

function checkPassword(id) {
    var password1_element = document.getElementById("newPassword");
    var password1 = password1_element.value;
    var password2_element = document.getElementById(id);
    var password2 = password2_element.value;

    if (password1 == password2) {
        showCheck(id);
    } else {
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
    console.log("aaa");

}