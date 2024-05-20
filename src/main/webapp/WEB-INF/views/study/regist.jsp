
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<style>
    .main-content{
        padding-top: 120px;
    }
    body.modal-open {
        padding-right: 1px !important;
    }
</style>
<body>
<%@ include file="../common/header.jsp"%>
<main class="main-content">


    <div class="card">
        <div class="card-body p-4">
            <form name="frmRegist" id="frmRegist" method="post" action="/study/regist" enctype="multipart/form-data">
                <input type="hidden" name="user_id" id="user_id " value="${memberDTO.user_id}">
              <%--  <div class="mb-3">
                    <label for="user_id" class="form-label">아이디</label>
                    <input type="text" class="form-control" name="user_id" id="user_id" value="${memberDTO.user_id}" readonly>

                </div>--%>
                <div class="mb-3">
                    <label for="title" class="form-label">제목</label>
                    <input type="text" class="form-control" name="title" id="title" maxlength="30">

                </div>
                <div class="mb-3">
                    <label for="content" class="form-label">학습내용</label>
                        <textarea class="form-control" name="content" id="content" rows="5" cols="60" style="resize: none;" maxlength="400"></textarea>

                </div>
                <div class="mb-3">
                    <label for="files" class="form-label">이미지</label>
                    <input class="form-control" type="file" name="files" id="files" multiple>
                </div>

               <div class="d-flex">
                    <div class="flex-grow-1 me-2">

                        <fieldset>
                            <legend style="font-size: 16px;">오늘의 학습 노출 여부</legend>
                            <label>
                                노출 <input type="radio" name="status" value="Y" checked />
                            </label>
                            <label>
                                노출안함 <input type="radio" name="status" value="N" />
                            </label>
                        </fieldset>



                    </div>

                    <div class="flex-grow-1 me-2">
                        <label for="exposure_start" class="form-label">오늘의 학습노출 시작일</label>
                        <input type="date" class="form-control" name="exposure_start" id="exposure_start">
                    </div>

                    <div class="flex-grow-1">
                        <label for="exposure_end" class="form-label">&nbsp; 종료일</label>
                        <input type="date" class="form-control" name="exposure_end" id="exposure_end">
                    </div>
                </div>
                <br>

                <div class="mb-6">
                    <label for="share_person" class="form-label">공유한사람</label>
                    <input type="text" class="form-control" name="share_person" id="share_person" readonly>
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
                        <button type="button" class="btn btn-primary justify-content-md-end" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="0" style="transform: translateY(-38px)">공유하기</button>
                    </div>

                </div>


                <div class="d-flex">
                    <div class="flex-grow-1 me-2">
                        <label for="field" class="form-label" >분야</label>
                        <input type="text" class="form-control" name="field" id="field">
                    </div>

                    <div class="flex-grow-1">
                        <label for="tag" class="form-label">해시태그</label>
                        <input type="text" class="form-control" name="tag" id="tag">
                    </div>



                </div>
                <br>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
                    <button type="button"  id="submit_button" class="btn btn-primary justify-content-md-end"  >등록하기</button>
                </div>



            </form>
        </div>
    </div>


</main>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">공유아이디 목록  </h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form name="searchid" id="searchid" action="/study/searchid">
                <div class="input-group mb-1">
                    <span class="input-group-text ">검색범위</span>
                    <div class="input-group-text">
                        <div class="form-check form-switch form-check-inline" >
                            <label class="form-check-label" for="search_type_0">아이디</label>
                            <input class="form-check-input" role="switch" type="checkbox" value="t" name="search_type" id="search_type_0" ${search_typeflag_0}>
                        </div>

                    </div>

                    <input class="form-control" type="text" name="search_word" id="search_word" placeholder="검색어" value="${responseDTO.search_word}">
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
                    <button class="btn btn-outline-primary" type="submit" id="search_button">검색</button>
                    <button class="btn btn-outline-primary" type="button" onclick="window.location.href='/study/mystudy'">검색 초기화</button>
                </div>
                    <hr>
                    <div class="mb-3" id="memberContainer">
                    <%--<c:forEach items="${member}" var="list" varStatus="status" >
                       <h5> <input type="checkbox" class="shard_user" id="shared_user${status.count}" name="shard_user" value="${list.user_id}" > ${list.user_id}<br></h5>
                    </c:forEach>--%>
                    </div>
                </form>


            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="close_regist">취소</button>
                 <button type="button" class="btn btn-primary" onclick="share()"> 공유 </button>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<Script>
    function share(){
        const shard_users = document.querySelectorAll(".shard_user:checked");
        const share_person = document.querySelector("#share_person");

        // 선택된 체크박스의 값들을 배열로 변환
        const selectedValues = Array.from(shard_users).map(function(checkbox) {
            return checkbox.value;
        });

        // 배열의 값들을 쉼표로 구분된 문자열로 합치기
        share_person.value = selectedValues.join(',');

        document.getElementById('close_regist').click();
    }


    const submit_button = document.querySelector("#submit_button");
    submit_button.addEventListener("click",function (e){
       e.preventDefault();

        const title = document.querySelector("#title");
        const content = document.querySelector("#content");
        const tag = document.querySelector("#tag");
        const field = document.querySelector("#field");

        if(title.value.length ==0){
            alert("제목을 입력해주세요.");

            return false;
        }
        if(content.value.length ==0){
            alert("내용을 입력해주세요.");

            return false;
        }
        if(tag.value.length ==0){
            alert("태그를 입력해주세요.");

            return false;
        }
        if(field.value.length ==0){
            alert("분야를 입력해주세요.");

            return false;
        }



       const confirm_msg="정말로 등록하시겠습니까?"
        if(confirm(confirm_msg)){
            document.frmRegist.submit();
        }


    });

    const radioButtons = document.querySelectorAll('input[name="status"]');
    const exposureStart = document.getElementById('exposure_start');
    const exposureEnd = document.getElementById('exposure_end');

    radioButtons.forEach(radio => {
        radio.addEventListener('change', function() {
            if (this.value === 'Y') {
                exposureStart.removeAttribute('readonly');
                exposureEnd.removeAttribute('readonly');
            } else if (this.value === 'N') {
                exposureStart.setAttribute('readonly', 'readonly');
                exposureEnd.setAttribute('readonly', 'readonly');
            }
        });
    });

    // 초기 상태 설정
    if (document.querySelector('input[name="status"]:checked').value === 'N') {
        exposureStart.setAttribute('readonly', 'readonly');
        exposureEnd.setAttribute('readonly', 'readonly');
    }

    const search_button = document.querySelector("#search_button");
    search_button.addEventListener("click",function(e){
       e.preventDefault();

        const search_word = $('#search_word').val();
        const search_type = $('#search_type_0').val();



        $.ajax({
            url: '/study/searchid',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json',
            data: {
                search_word: search_word,
                search_type:search_type
            }, // 수정된 부분

            success: function(response) {


                if(response.success) {
                    const members = response.data.dtoList; // 서버로부터 받은 사용자 목록 데이터
                    const memberContainer = $('#memberContainer'); // 사용자 목록을 삽입할 컨테이너
                    memberContainer.empty(); // 기존 내용을 지우고 새로운 목록을 추가하기 위해 컨테이너를 비웁니다.

                    $.each(members, function(index, member) {
                        const memberElement = '<h5>' +
                            '<input type="checkbox" class="shard_user" id="shared_user' + (index + 1) + '" name="shard_user" value="' + member.user_id + '">' +' &nbsp;아이디 : &nbsp;' +
                            member.user_id + '<br>' +
                            '</h5>';
                        memberContainer.append(memberElement);
                    });

// 페이지네이션 생성




                } else {
                    alert(response.message);

                }
            },
            error: function() {
                alert("서버와의 통신 중 오류가 발생했습니다.");
            }
        });
    });
    // 페이지 이동을 처리하는 함수



</Script>
</body>
</html>
