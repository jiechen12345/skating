function alertBox(type, msg, showTime, callBack) {
    var divCss = "alert alert-" + type + " alert-dismissable";
    if (showTime == null) showTime = 1000;
    var divAlertBox;
    if ($("#divAlertBox").length != 0) {
        $("#divAlertBox span").text(msg);
        divAlertBox = $("#divAlertBox");
        divAlertBox.removeClass().addClass(divCss).slideDown(500);
        box(divAlertBox);
    } else {
        divAlertBox = $("<div id='divAlertBox' style='display:none;position:fixed;z-index:9999;' class='" + divCss + "'><button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button><span>" + msg + "</span></div>");
        $("body").append(divAlertBox);
        divAlertBox.slideDown(700);
        box(divAlertBox);
    }
    setTimeout(function () {
        divAlertBox.fadeOut(1100);
        if (callBack != null) {
            callBack();
        }
    }, showTime);
}

function box(jqObj) {
    var oBox = jqObj[0];
    var L1 = oBox.clientWidth;
    var H1 = oBox.clientHeight;
    var Left = (document.documentElement.clientWidth - L1) / 2;
    var top = (document.documentElement.clientHeight - H1) / 4;
    oBox.style.left = Left + 'px';
    oBox.style.top = top + 'px';
}

function alertWarning(msg, showTime, callBack) {
    alertBox("warning", msg, showTime, callBack);
}

function alertDanger(msg, showTime, callBack) {
    alertBox("danger", msg, showTime, callBack);
}

function alertSuccess(msg, showTime, callBack) {
    alertBox("success", msg, showTime, callBack);
}

function alertInfo(msg, showTime, callBack) {
    alertBox("info", msg, showTime, callBack);
}
