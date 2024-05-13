
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <script src="/resources/js/inputValidate.js"></script>
    <style>
        #frm{

            border:1px solid black;
            height:500px;
            margin : 0px auto;
            padding : 20px;
        }
    </style>
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">
<%@ include file="../common/header.jsp"%>


    <div class="container">
    <form id="frm" name="frm" method="post" action="/login/find" >
        <h1>패스워드를 잊으셨나요?</h1>
        <p>본인인증을 진행하기 위해 아이디를 입력하여 주세요.</p>
        <div class="d-flex" style="justify-content: center; align-items: center">
            <label for="pwd" >아이디</label>&nbsp;
                <input type="text" name="pwd" id="pwd" class="form-control form-control-lg me-2" style="width: 400px">
            <input type="button" id="search_pwd" class="btn btn-primary btn-lg" value="임시비밀번호찾기">

        </div>
        <br><br><br>
        <div style="text-align: center">
            <h4 >
                ※ 아이디 입력 후 임시비밀번호 발송 버튼을 눌러주세요.<br>
                ※ 가입하신 이메일 주소로 임시비밀번호가 발송됩니다.<br>
                메일 서비스에 따라 다소 시간이 걸릴 수 있습니다.<br>
                ※ 임시비밀번호는 발송 후, 1시간 내에만 유효합니다.<br>
            </h4>

        </div>

    </form>
    </div>
<script>

    //유효성검사
    validateInput('pwd');
    const search_pwd = document.querySelector("#search_pwd");
    search_pwd.addEventListener("click",function(e){
       e.preventDefault();
      const checkBlank= validateBlank('pwd');
      if(checkBlank){
          document.frm.submit();
      }

    });




</script>


</body>
</html>
