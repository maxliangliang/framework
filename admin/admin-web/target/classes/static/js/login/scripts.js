
$(document).ready(function() {


    // $('.page-container form .username, .page-container form .password').keyup(function(){
    //     $(this).parent().find('.error').fadeOut('fast');
    // });

});


function btn_submit(){
    var username = $(this).find('.username').val();
    var password = $(this).find('.password').val();
    var captcha = $(this).find('.captcha').val();
    if(username == '') {
        $(this).find('.error').fadeOut('fast', function(){
            $(this).css('top', '27px');
        });
        $(this).find('.error').fadeIn('fast', function(){
            $(this).parent().find('.username').focus();
        });
        return false;
    }

    if(password == '') {
        $(this).find('.error').fadeOut('fast', function(){
            $(this).css('top', '96px');
        });
        $(this).find('.error').fadeIn('fast', function(){
            $(this).parent().find('.password').focus();
        });
        return false;
    }

    if(captcha == '') {
        $(this).find('.error').fadeOut('fast', function(){
            $(this).css('top', '96px');
        });
        $(this).find('.error').fadeIn('fast', function(){
            $(this).parent().find('.captcha').focus();
        });
        return false;
    }


    $.ajax({
        cache: false,
        type: "POST",
        dataType:'json',
        url: "toLogin.do",
        data:$('#form').serialize(),// 你的formid
        async: false,
        error: function(request) {
            $("#errormsg").text("系统错误！");
        },
        success: function(data) {
            if(data.status=='ok'){
                window.location.href=projectpath()+data.url;
            }else{
                $("#errormsg").text(data.msg);
                $("#captcha").val("");
                refreshCaptcha();
            }
        }
    });
}


function refreshCaptcha() {
    $("#kaptcha").attr("src",projectpath()+"/kaptchaImage.jpg?t=" + Math.random());
}