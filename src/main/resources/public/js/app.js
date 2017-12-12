/**
 * Created by 00013708 on 2017/11/22.
 */

//先来搞个form的，然后再来搞个ajax的吧
$(document).ready(function () {
    //add tweet
    $("#submit").click(function () {
        var newTweet = $("#newTweet").val();
        var tweetVo = {"newTweet": newTweet, "uid": 1};
        //send ajax
        $.ajax({
            type: "POST",
            url: "/tweet/add",
            data: JSON.stringify(tweetVo),
            dataType: "html",
            contentType: "application/json; charset=utf-8",
            cache: false,
            success: function (data) {
                console.info("data-------------" + data);
                $("#newTweet").val('');
                $("#tweetList").html(data);

            }
        });
    });

    /*$(document).click(function(e) {
        /!*if( e.target.id != 'info') {
            $("#info").hide();
        }*!/
        var content = e.innerHTML;
        console.info("content:"+content);
        if(e.target.contenteditable&&){

        }
    });*/

});

document.execCommand("DefaultParagraphSeparator", false, "br");
//del tweet
function delTweet(tid) {
    var data = {"tid": tid};
    // var respHtml = ajaxJH("/tweet/del", data);
    $.ajax({
        type: "POST",
        url: "/tweet/del",
        data: JSON.stringify(data),
        dataType: "html",
        contentType: "application/json; charset=utf-8",
        cache: false,
        success: function (data) {
            $("#tweetList").html(data);
        }
    })
}
//用ajax发送json，返回html
function ajaxJH(url, data) {
    return $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "html",
        contentType: "application/json; charset=utf-8",
        cache: false
    });
}
// function  editable() {
//     $(this).attribute();
// }