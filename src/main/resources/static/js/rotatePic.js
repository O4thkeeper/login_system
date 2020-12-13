let moving = false
let $banner = $(".picContainer a")
let baIndex = 0
let baInterval = setInterval(function () {
    baIndex = easyNextPage(baIndex, (baIndex + 1) % 5, $banner, $(".picBtn button"), "buttonHover")
}, 4000)

$(".bodyLeft").hover(function () {
    clearInterval(baInterval)
}, function () {
    baInterval = setInterval(function () {
        baIndex = easyNextPage(baIndex, (baIndex + 1) % 5, $banner, $(".picBtn button"), "buttonHover")
    }, 5000)
})

$(".picBtn button").each(function (index) {
    $(this).click(function () {
        if (moving) return

        baIndex = easyNextPage(baIndex, index, $banner, $(".picBtn button"), "buttonHover")
    })
})

function easyNextPage(pre, index, $picTarget, $btnTarget, btnNextClass) {

    if (moving) return pre
    moving = true
    $($picTarget.get(pre)).fadeOut(600, function () {
        $($picTarget.get(index)).fadeIn(1600)
        $($btnTarget.get(index)).addClass(btnNextClass)
        $($btnTarget.get(pre)).removeClass(btnNextClass)
    })

    moving = false
    return index
}
