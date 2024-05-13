
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <style>

        #frm{

            border:1px solid black;
            height:600px;
            margin : 0px auto;
            padding : 20px;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
<%@ include file="../common/header.jsp"%>
<div class="container">
    <form id="frm" name="frm" method="post" action="/login/update" >
        <br>
        <h3 style="text-align: center">개인정보 보호를 위해 패스워드를 변경해주세요</h3>
        <br>
        <div class="d-flex" style="justify-content: center; align-items: center">
            <label for="prior_pwd" >이전 패스워드</label>&nbsp;
            <input type="text" name="prior_pwd" id="prior_pwd" class="form-control form-control-lg" style="width: 400px">&nbsp;
            <div class="input-group-append">
                <span class="input-group-text"  id="icon_prior_pwd" style="background: white;border: none;"><i class="fas fa-lock"  style="color:gray"></i></span>
            </div>

        </div>
        <br>
        <div class="d-flex" style="justify-content: center; align-items: center">
            <label for="new_pwd" >새 패스워드&nbsp;&nbsp;</label>&nbsp;
            <input type="text" name="new_pwd" id="new_pwd" class="form-control form-control-lg" style="width: 400px">&nbsp;
            <div class="input-group-append">
                <span class="input-group-text"  id="icon_new_pwd" style="background: white;border: none;"><i class="fas fa-lock" style="color:gray" ></i></span>
            </div>
        </div>
        <div id="password_guide" style="display:none; color:red; text-align: center">
            8자 이상의 영문 소문자, 숫자, 특수문자를 조합해 주세요.
        </div>
        <br>
        <div class="d-flex" style="justify-content: center; align-items: center">
            <label for="confirm_pwd" >비밀번호 확인</label>&nbsp;
            <input type="text" name="confirm_pwd" id="confirm_pwd" class="form-control form-control-lg" style="width: 400px">&nbsp;
            <div class="input-group-append">
                <span class="input-group-text"  id ="icon_confirm_pwd" style="background: white;border: none;"><i class="fas fa-lock" style="color:gray" ></i></span>
            </div>

        </div>
        <div  id="pwd_mismatch_msg2" style="display:none;color:red; text-align: center">비밀번호가 일치하지 않습니다.</div>

        <br><br>
        <div style="text-align: center">
            <h7 >
                ※ 10자 이상의 영문 소문자, 숫자, 특수문자를 모두 조합하여 설정하여 주세요.<br>
                ※ 아이디와 같은 패스워드는 사용할 수 없습니다.<br>
                ※ 생년월일, 주민등록번호, 연속된 숫자 등의 패스워드 사용은 삼가해주세요.<br>
            </h7>
            <br><br>

        </div>
        <div class="d-flex justify-content-center" style="gap:50px">
            <button class="btn btn-primary btn-lg" type="button" style="width: 200px">확 인</button>
            <button class="btn btn-primary btn-lg"  type="button" style="width: 200px">취 소</button>
        </div>

    </form>
    </div>

<Script>
    document.getElementById("prior_pwd").addEventListener("input", function() {
    var icon = document.getElementById("icon_prior_pwd").getElementsByTagName("i")[0];
    if (this.value.length > 0) {
    icon.style.color = "green";
    } else {
    icon.style.color = "gray";
    }
    });

    document.getElementById("new_pwd").addEventListener("input", function() {
        var icon = document.getElementById("icon_new_pwd").getElementsByTagName("i")[0];
        var passwordGuide = document.getElementById("password_guide");
        if (validateNewPassword(this.value)) {
            icon.style.color = "green";
            passwordGuide.style.display = "none"; // 비밀번호가 유효할 때 가이드 문구 숨김
        } else {
            icon.style.color = "red";
            passwordGuide.style.display = "block"; // 비밀번호가 유효하지 않을 때 가이드 문구 표시
        }
    });

    function validateNewPassword(password) {
        // 여기서는 아이디와 이전 패스워드 검증은 예시로 제외하고, 기본적인 조건만 검증
        var minLength = 8;
        var hasLowercase = /[a-z]/.test(password);
        var hasNumbers = /\d/.test(password);
        var hasSpecialChars = /[!@#$%^&*(),.?":{}|<>]/.test(password);

        return password.length >= minLength && hasLowercase && hasNumbers && hasSpecialChars;
    }

    document.getElementById("confirm_pwd").addEventListener("input", function() {
        var newPassword = document.getElementById("new_pwd").value;
        var confirmPassword = this.value;
        var icon = document.getElementById("icon_confirm_pwd").getElementsByTagName("i")[0];
        var mismatchMsg2 = document.getElementById("pwd_mismatch_msg2");

        if (confirmPassword.length > 0) {
            if (newPassword === confirmPassword) {
                icon.style.color = "green";
                mismatchMsg2.style.display = "none";
            } else {
                icon.style.color = "gray";
                mismatchMsg2.style.display = "block";
            }
        } else {
            icon.style.color = "gray";
            mismatchMsg2.style.display = "none";
        }
    });

</Script>
</body>
</html>
