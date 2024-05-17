
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
    <link href="/resources/css/study.css" rel="stylesheet">
    <style>
        .main-content{
            padding-top: 120px;

        }
        .team .team-member span {
            border-bottom: 1px solid black !important;;
        }
        #prevDay{
            cursor:pointer;
        }
        #nextDay{
            cursor:pointer;
        }

    </style>
</head>
<body>
<%@ include file="../common/header.jsp"%>
<main class="main-content">
    <section id="team" class="team section" >

        <!-- Section Title -->
        <div class="container section-title aos-init aos-animate" data-aos="fade-up">
            <h2>  <i class="bi bi-arrow-left-circle-fill" id="prevDay" ></i> &nbsp;
                <span id="studyText">오늘의 학습</span> &nbsp;  <i class="bi bi-arrow-right-circle-fill" id="nextDay" ></i></h2>
            <h4><div id="currentDate"></div></h4>
        </div><!-- End Section Title -->

        <div class="container">

            <div class="row gy-4">
                <c:forEach items="${studyDTO}" var="list" begin="0" end="3" varStatus="count">
                <div class="col-lg-6 aos-init aos-animate" data-aos="fade-up" data-aos-delay="100">


                    <div class="team-member d-flex align-items-start">
                        <div class="pic"><img src="/resources/img/${list.img}" class="img-fluid" alt=""  style="height: 200px !important; width: 200px !important;"></div>
                        <div class="member-info">
                            <h4> <a href="/study/view?no=${list.no}" style="text-decoration: none;">${list.title}</a></h4>
                            <span>${list.field} &nbsp; #${list.tag}</span>
                            <p>${list.content}</p>
                            <br><br>
                            <div class="social">
                                                               <c:forEach items="${list.sharedUsers}" var="user">
                                    <a href="" style="width: 60px !important;  text-decoration: none;">${user.shared_by_user_id}</a>
                                </c:forEach>

                            </div>
                        </div>
                    </div>

                </div><!-- End Team Member -->
                </c:forEach>


            </div>

        </div>

    </section>

</main>
<%@ include file="../common/footer.jsp"%>
<script>

    document.addEventListener('DOMContentLoaded', function() {
        let currentDate = new Date(); // 현재 날짜와 시간
        currentDate.setHours(0, 0, 0, 0); // 시간을 00:00:00.000으로 설정
        const today = new Date(); // 처음 페이지를 로드할 때의 날짜 (오늘)
        today.setHours(0, 0, 0, 0); // 시간을 00:00:00.000으로 설정

        // 날짜를 YYYY-MM-DD 형태의 문자열로 변환하는 함수
        function formatDate(date) {
            let d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;

            return [year, month, day].join('-');
        }


        // 현재 날짜 표시 및 학습 텍스트 업데이트
        function displayCurrentDate() {
            document.getElementById('currentDate').textContent = formatDate(currentDate);

            // 현재 날짜와 오늘 날짜 비교하여 텍스트 업데이트
            if(currentDate.toISOString() === today.toISOString()) {
                document.getElementById('studyText').textContent = "오늘의 학습";
            } else {
                document.getElementById('studyText').textContent = "이전의 학습";
            }
        }
        displayCurrentDate();

        // 이전 날짜로 이동
        document.getElementById('prevDay').addEventListener('click', function() {
            currentDate.setDate(currentDate.getDate() - 1); // 날짜에서 하루 빼기
            displayCurrentDate();
            // 변경된 날짜로 데이터를 가져오는 로직 추가
        });

        // 다음 날짜로 이동
        document.getElementById('nextDay').addEventListener('click', function() {
            let tempDate = new Date(currentDate);
            tempDate.setDate(tempDate.getDate() + 1);

            if (tempDate <= today) {
                currentDate = tempDate;
                displayCurrentDate();
                // 변경된 날짜로 데이터를 가져오는 로직 추가
            }
        });



    });
</script>
</body>
</html>
