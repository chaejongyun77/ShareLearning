
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <style>
        .main-content{
            padding-top: 120px;
        }
    </style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<main class="main-content">
    <form id="frm" name="frm" method="post" action="/member/mypage">
        <div class="row g-3">
            <div class="col-sm-6">
                <label for="user_id" class="form-label">아이디 </label>
                <input type="text" class="form-control" id="user_id" name="user_id" value="${sessionScope.memberDTO.user_id}" readonly>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>

            <div class="col-sm-6">
                <label for="name" class="form-label">이름 </label>
                <input type="text" class="form-control" id="name" name="name" value="${sessionScope.memberDTO.name}" readonly>

            </div>

            <div class="col-12">
                <label for="pwd" class="form-label">패스워드</label>
                <div class="input-group has-validation">
                    <input type="text" class="form-control" id="pwd" name="pwd" >

                </div>
            </div>

            <div class="col-12">
                <label for="confirm_pwd" class="form-label">패스워드 확인</label>
                <input type="email" class="form-control" id="confirm_pwd" >

            </div>


            <div class="col-md-4">
                <label for="number1" class="form-label">휴대폰번호</label>
                <select class="form-select" id="number1" name="number1">
                    <option value="010">010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                </select>

            </div>

            <div class="col-md-4">
                <label for="number2" class="form-label">&nbsp; </label>
                <input type="email" class="form-control" name="number2" id="number2" value="${fn:substring(sessionScope.memberDTO.phoneNumber, 3, 7)}" >

            </div>

            <div class="col-md-4">
                <label for="number3" class="form-label">&nbsp;</label>
                <input type="text" class="form-control" name="number3" id="number3" value="${fn:substring(sessionScope.memberDTO.phoneNumber, 7, 11)}">

            </div>

            <div class="col-md-6">
                <label for="email1" class="form-label">이메일 </label>
                <input type="text" class="form-control" id="email1" name="email1" value="${fn:substring(sessionScope.memberDTO.email, 0, fn:indexOf(sessionScope.memberDTO.email, '@'))}">
            </div>
            <div class="col-md-6">
                <label for="email2" class="form-label">&nbsp; </label>
                <select class="form-select" id="email2"  name= email2 >
                    <option value="@naver.com">@naver.com</option>
                    <option value="@daum.net">@daum.net</option>
                    <option value="@hanmail.net">@hanmail.net</option>
                    <option >직접입력</option>
                </select>

            </div>


        </div>

        <br>

        <div style="text-align: center">
        <button class="w-40 btn btn-primary btn-lg" type="button" id="modify_button">수정</button>
        <button class="w-40 btn btn-primary btn-lg" type="submit">취소</button>
        </div>
    </form>

</main>
<%@ include file="../common/footer.jsp"%>
<script>
    const modify_button = document.querySelector("#modify_button");
    modify_button.addEventListener("click",function (e){
       e.preventDefault();
        const confirm_msg = "정말로 수정하시겠습니까? ";
        if (confirm(confirm_msg)){
            document.frm.submit();
        }


    });

</script>
</body>
</html>
