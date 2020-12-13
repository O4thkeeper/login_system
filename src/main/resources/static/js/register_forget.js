/*验证两次密码是否一致*/
$(".registerBtn").click(function () {
    if(!($("#regPassword").val()===$("#regConfirm").val())){
        $("#regTip").removeClass("hiddenForm")
        $("#regConfirm").focus()
        return false
    }else{
        $("#regTip").addClass("hiddenForm")
    }
})

/*发送手机验证码请求*/
$("#registerForm .codeBtn").click(function () {
    let codeUrl = $(".regCodeForm").attr("action")
    let phoneNum = $("#registerForm .phoneInput").val()
    if(!(/^1[34578]\d{9}$/.test(phoneNum))){
        alert("手机号码格式错误！")
        $("#registerForm .phoneInput").focus()
        return false
    }
    $.ajax({
        url: codeUrl + "?phone=" + phoneNum,
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data) alert("已发送，注意查收！")
            else alert("手机号码已被注册")
        },
        error: function () {
            alert("发生错误，请重新输入")
        }
    })
    return false
})
$("#forgetForm .codeBtn").click(function () {
    let codeUrl = $(".codeForm").attr("action")
    let phoneNum = $("#forgetForm .phoneInput").val()
    if(!(/^1[34578]\d{9}$/.test(phoneNum))){
        alert("手机号码格式错误！")
        $("#forgetForm .phoneInput").focus()
        return false
    }
    $.ajax({
        url: codeUrl + "?phone=" + phoneNum,
        type: 'post',
        dataType: 'json',
        success: function (data) {
            if (data) alert("已发送，注意查收！")
            else alert("手机号码未注册")
        },
        error: function () {
            alert("发生错误，请重新输入")
        }
    })
    return false
})

/*小页面切换*/
let $reForm = $("#registerForm")
let $foForm = $("#forgetForm")
let $reBtn = $("#registerBtn")
let $foBtn = $("#forgetBtn")

$reBtn.mouseenter(function () {
    $reBtn.removeClass("btnNotHover")
    $foBtn.addClass("btnNotHover")
    $reForm.removeClass("hiddenForm")
    $foForm.addClass("hiddenForm")
})
$foBtn.mouseenter(function () {
    $foBtn.removeClass("btnNotHover")
    $reBtn.addClass("btnNotHover")
    $reForm.addClass("hiddenForm")
    $foForm.removeClass("hiddenForm")
})