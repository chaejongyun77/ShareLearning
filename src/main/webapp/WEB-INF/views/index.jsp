<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <link href="/resources/css/common.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<style>
    .main-content{
        padding-top: 120px;
    }
    .btn.btn-primary.btn-lg.px-4.gap-3{
        background: #C58917;
        border: 1px solid #C58917;
    }
</style>
</head>
<body>
<%@ include file="./common/header.jsp"%>
<main class="main-content">

    <div class="px-2 py-3 my-3 text-center" style="height: 250px; " >
        <h3 class="display-5 fw-bold text-body-emphasis">학습공유 사이트</h3>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">가장 많은것을 알고 있는 사람이 <br> 인생에서 가장 크게 성공한다</p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <button type="button" class="btn btn-primary btn-lg px-4 gap-3" onclick="location.href='/study/mystudy'"> 학습하러 가기</button>

            </div>
        </div>
    </div>




    <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2" class=""></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3" class="active" aria-current="true"></button>
        </div>

        <div class="carousel-inner">
            <div class="carousel-item">
                <img src="/resources/img/study1.png" alt="First slide" style="width:100%; height:450px; object-fit: cover;">
                <div class="container">
                    <div class="carousel-caption text-start">

                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/resources/img/study2.png" alt="First slide" style="width:100%; height:450px; object-fit: cover;">                <div class="container">
                    <div class="carousel-caption">

                    </div>
                </div>
            </div>
            <div class="carousel-item active">
                <img src="/resources/img/study3.png" alt="First slide" style="width:100%; height:450px; object-fit: cover;">                <div class="container">
                    <div class="carousel-caption text-end">

                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>



</main>

    <%@ include file="./common/footer.jsp"%>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>