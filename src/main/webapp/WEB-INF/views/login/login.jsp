
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>

   <%-- <link rel="stylesheet" href="/resources/css/signin.css" />--%>
    <link href="/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<Style>

  /*  html,body {
        width: 1024px; !* 전체 페이지의 가로 크기를 1024px로 설정 *!
        margin: 0 auto; !* 페이지를 화면 중앙에 정렬 *!

    }*/
</Style>
</head>


<body class="d-flex align-items-center py-4 bg-body-tertiary">


<div class="container" >
    <%@ include file="../common/header.jsp"%>

    <form id="frm" name="frm" method="post" action="/login/login">

        <h1 class="h3 mb-3 fw-normal">Login</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="user_id" name="user_id" value="${cookie.save_id.value}" >
            <label for="user_id">아이디</label>
        </div>
        <br>
        <div class="form-floating">
            <input type="text" class="form-control" id="pwd" name="pwd" >
            <label for="pwd">패스워드</label>
        </div>

        <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" role="switch" name="save_id" id="save_id" value="checked" ${cookie.save_id_flag.value}>
            <label class="form-check-label" for="save_id">
               아이디 저장

            </label>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button class="btn btn-primary" type="button" onclick="location.href='/login/find'">패스워드찾기</button>
            <button class="btn btn-primary" id= "loginButton" type="button" disabled >로그인</button>
        </div>

    </form>


</div>
<%@ include file="../common/footer.jsp"%>
<script>

    var inputField = document.getElementById('user_id');

    inputField.oninput = function() {
        // 입력된 값에 영어 소문자 및 숫자만 포함되어 있는지 확인
        var validPattern = /^[a-z0-9]+$/;
        if (!validPattern.test(inputField.value)) {
            // 유효하지 않은 문자가 포함된 경우 경고
            alert("영어 소문자 및 숫자만 입력 가능합니다.");
            inputField.value = ""; // 입력 필드 초기화
        }
    };
    var loginButton = document.getElementById('loginButton');
    var passwordInput = document.getElementById('pwd');
    document.getElementById('pwd').addEventListener('input', function() {

        if (passwordInput.value.length >= 8) {
            loginButton.disabled = false;
        } else {
            loginButton.disabled = true;
        }
        var password = this.value;
        var koreanPattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if(koreanPattern.test(password)) {
            alert("영어+숫자+특수문자 조합으로 최소 8자리 이상만 허용됩니다.");
            this.value = ''; // 입력 필드 초기화
        }
    });


    //ajax 로그인 시도
    $(document).ready(function() {
        $('#loginButton').click(function(e) {
            e.preventDefault(); // 폼 전송 기본 이벤트 방지
            const userId = $('#user_id').val();
            const pwd = $('#pwd').val();

            if(userId === "" || pwd === "") {
                alert("아이디나 비밀번호를 모두 입력해주세요.");
                return false; // 이후 로직 실행을 막기 위해 함수 실행을 여기서 중단합니다.
            }
            $.ajax({
                url: '/login/login',
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({
                    user_id: userId,
                    pwd: pwd
                }),

                success: function(response) {

                    // 로그인 성공 처리
                    if(response.success) {
                        window.location.href = response.redirect; // 지정된 URL로 리다이렉트
                    } else {
                        // 로그인 실패 메시지
                        alert(response.message);
                    }
                },
                error: function() {
                    alert("입력하신 아이디 또는 패스워드가 일치하지 않습니다.");
                }
            });
        });
    });


</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>
