/*图片验证码点击切换*/
$(".verifyImg").click(function () {
    $(".verifyImg").attr("src", $(".verifyImg").attr("toGet") + "?random=" + Math.random())
})

/*发送手机验证码*/
$("#phoneLogForm .codeBtn").click(function () {
    let codeUrl = $(".codeForm").attr("action")
    let phoneNum = $("#phoneLogForm .phoneInput").val()
    if(!(/^1[34578]\d{9}$/.test(phoneNum))){
        alert("手机号码格式错误！")
        $("#phoneLogForm .phoneInput").focus()
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

/*头部页面切换*/
let $loForm = $("#loginForm")
let $phForm = $("#phoneLogForm")
let $loBtn = $("#loginBtn")
let $phBtn = $("#phoneBtn")

$loBtn.mouseenter(function () {
    $loBtn.removeClass("btnNotHover")
    $phBtn.addClass("btnNotHover")
    $loForm.removeClass("hiddenForm")
    $phForm.addClass("hiddenForm")
})
$phBtn.mouseenter(function () {
    $phBtn.removeClass("btnNotHover")
    $loBtn.addClass("btnNotHover")
    $loForm.addClass("hiddenForm")
    $phForm.removeClass("hiddenForm")
})

