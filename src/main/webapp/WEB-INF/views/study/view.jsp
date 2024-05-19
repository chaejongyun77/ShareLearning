
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <style>
        .main-content{
            padding-top: 120px;
        }
      /*  .fit-picture {
            width: 100%;
            height: 100%;

            object-fit:cover;
        }*/
        .icon-large{
            font-size:50px;
            pointer:cursor;
        }

    </style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<main class="main-content">

    <div class="card">
        <div class="card-body p-4">
            <form name="frmRegist" id="frmRegist" method="post" action="/study/regist" enctype="multipart/form-data">
                <input type="hidden" name="study_idx" id="study_idx" class="study_idx" value="${studyDTO.no}" data-study_idx="${studyDTO.no}">

                <div class="mb-3">
                    <label for="title" class="form-label">제목</label>
                    <input type="text" class="form-control" name="title" id="title" maxlength="30" value="${studyDTO.title}" readonly>

                </div>
                <div class="d-flex">

                    <div class="flex-grow-1 me-2">
                        <label for ="fit-picture" class="form-label">이미지&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <img
                                id="fit-picture"
                                class="fit-picture"
                                src="/resources/upload/${studyDTO.img}"
                                alt=""
                                onerror="this.src='/resources/img/noimage2.png'"
                                style="object-fit: cover; width: 400px; height: 200px;"
                        />


                    </div>
                    <div class="flex-grow-1" >
                    <label for="content" class="form-label" >학습내용</label>
                    <textarea class="form-control" name="content" id="content" rows="8" cols="60" style="resize: none;" maxlength="400" contentEditable="true" readonly>${studyDTO.content}</textarea>
                </div>

                </div>

                <br>
                <div class="d-flex">
                    <div class="flex-grow-1 me-2">

                        <fieldset>
                            <legend style="font-size: 16px;">오늘의 학습 노출 여부</legend>
                            <label>
                                노출 <input type="radio" name="status" value="Y" <c:if test="${studyDTO.status == 'Y'}">checked  </c:if>  disabled  />
                            </label>
                            <label>
                                노출안함 <input type="radio" name="status" value="N"  <c:if test="${studyDTO.status == 'N'}">checked  </c:if> disabled />
                            </label>
                        </fieldset>



                    </div>

                    <div class="flex-grow-1 me-2">
                        <label for="exposure_start" class="form-label">오늘의 학습노출 시작일</label>
                        <input type="date" class="form-control" name="exposure_start" id="exposure_start" value="${studyDTO.exposure_start}" readonly>
                    </div>

                    <div class="flex-grow-1">
                        <label for="exposure_end" class="form-label">&nbsp; 종료일</label>
                        <input type="date" class="form-control" name="exposure_end" id="exposure_end" value="${studyDTO.exposure_end}" readonly>
                    </div>
                </div>
                <br>

                <div class="mb-6">
                    <label for="share_person" class="form-label">공유한사람</label>
                    <input type="text" class="form-control" name="share_person" id="share_person" readonly value="
<c:forEach items="${studyDTO.share_person}" var="person" varStatus="status">
    <c:out value="${person}"/>
    <c:if test="${!status.last}">,</c:if>
</c:forEach>
">
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">

                    </div>

                </div>
                    <br>

                <div class="d-flex">
                    <div class="flex-grow-1 me-2">
                        <label for="field" class="form-label" >분야</label>
                        <input type="text" class="form-control" name="field" id="field" value="${studyDTO.field}" readonly>
                    </div>

                    <div class="flex-grow-1">
                        <label for="tag" class="form-label">해시태그</label>
                        <input type="text" class="form-control" name="tag" id="tag" value="${studyDTO.tag}" readonly>
                    </div>



                </div>
                <br>
                <div class="d-flex" style="justify-content: center">

                    <input type="image" src="/resources/img/like.png" data-user="${sessionScope.memberDTO.user_id}"  id="btn_like" class= "btn_like" name="btn_like" alt="좋아요"  data-study="${studyDTO.user_id}" >   <%--<i class="bi bi-chat-heart-fill icon-large" id="btn_like"></i>--%> &nbsp;<%--<span style="font-size: 30px">0</span>--%> <div id="result" style="font-size: 30px">${studyDTO.like}</div>

                </div>
                <div class="d-flex me-2" style="justify-content: flex-end;">

                <div class="me-2">
                    <button type="button"  id="list_button" class="btn btn-primary justify-content-md-end"  onclick="location.href='/study/mystudy'" >목록</button>
                </div>

                    <c:if test="${sessionScope.memberDTO.user_id eq studyDTO.user_id}">
                <div class="me-2">
                    <button type="button"  id="submit_button" class="btn btn-primary justify-content-md-end" onclick="location.href='/study/regist'"  >등록</button>
                </div>
                <div class="me-2">
                    <button type="button"  id="modify_button" class="btn btn-primary justify-content-md-end"  onclick="location.href='/study/modify?no=${studyDTO.no}'">수정</button>
                </div>

                    <div class="me-2">
                <button type="button"  id="delete_button" class="btn btn-primary justify-content-md-end" onclick="location.href='/study/delete?no=${studyDTO.no}'" >삭제</button>
                    </div>
                    </c:if>

                </div>



            </form>
        </div>
    </div>

</main>
<%@ include file="../common/footer.jsp"%>
<Script>

    const delete_button = document.querySelector("#delete_button");
    delete_button.addEventListener("click",function(e){
       e.preventDefault();
       const confirm_msg = "정말로 삭제하시겠습니까?";
       if(confirm(confirm_msg)){
           alert("삭제되었습니다.");
           const study_idx = document.querySelector("#study_idx");
           location.href='/study/delete?no='+study_idx.value;
       }

    });


  const btn_like = document.querySelector("#btn_like");


    btn_like.addEventListener("click",function(e){
     e.preventDefault();
     const user_id = btn_like.getAttribute("data-user");
     const study_id = btn_like.getAttribute("data-study");
     if(user_id === study_id){
         alert("작성자는 추천할 수 없습니다.");
         return false;
     }




    const study_idx = $('#study_idx').val();


    $.ajax({
        url: '/study/like',
        type: 'POST',
        contentType: 'application/x-www-form-urlencoded',
        dataType: 'json',
        data: { study_idx: study_idx }, // 수정된 부분

        success: function(response) {


            if(response.success) {
                alert(response.message);
                window.location.href = response.redirect;

            } else {
                alert(response.message);

            }
        },
        error: function() {
            alert("서버와의 통신 중 오류가 발생했습니다.");
        }
    });



    });
</Script>

</body>
</html>
