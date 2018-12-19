
function showCross(id) {
    var tmp = id+"Pic"
    var image = document.getElementById(tmp);
    image.src = "/img/cross_icon.png";
    image.width = 20;
    image.height = 20;
    image.style = "";
}

function showCheck(id) {
    var tmp = id+"Pic"
    var image = document.getElementById(tmp);
    image.src = "/img/icon-check.png";
    image.width = 20;
    image.height = 20;
    image.style = "";
}

function isName(id) {
    var name_element = document.getElementById(id);
    var name = name_element.value;
    var tmpCk = id+"Check";
    var tmpCr = id+"Cross";

    if (name != "aaa01") {
        showCross(id);
    } else {
        showCheck(id);
    }
}

function isAccount(id) {
    var account_element = document.getElementById(id);
    var account = account_element.value;
    //should be correct formation


    if (account != "aaa01") {
        showCross(id);
    } else {
        showCheck(id);
    }
}

function isPassword(id) {
    var password_element = document.getElementById("newPassword");
    var password = password_element.value;
    //should be correct formation and length
    if (password != "123") {
        showCross(id);
    } else {
        showCheck(id);
    }
}

function checkPassword(id) {
    var password1_element = document.getElementById("newPassword");
    var password1 = password1_element.value;
    var password2_element = document.getElementById(id);
    var password2 = password2_element.value;

    if (password1 != password2) {
        showCross(id);
    } else {
        showCheck(id);
    }
}
