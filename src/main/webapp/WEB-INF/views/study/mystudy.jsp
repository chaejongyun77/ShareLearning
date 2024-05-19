
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
    <link href="/resources/css/common.css" rel="stylesheet">
</head>
<style>
    .main-content{
        padding-top: 120px;
    }
</style>
<body>
<%@ include file="../common/header.jsp"%>
<main class="main-content">
    <div>
        <form name="frmSearch" id="search" action="/study/mystudy">
            <div class="input-group mb-1">
                <span class="input-group-text ">검색범위</span>
                <div class="input-group-text">
                    <div class="form-check form-switch form-check-inline" >
                        <label class="form-check-label" for="search_type_0">제목</label>
                        <input class="form-check-input" role="switch" type="checkbox" value="t" name="search_type" id="search_type_0" ${search_typeflag_0}>
                    </div>

                </div>

                <input class="form-control" type="text" name="search_word" id="search_word" placeholder="검색어" value="${responseDTO.search_word}">
            </div>
            <div class="input-group mb-1">
                <span class="input-group-text">검색기간</span>
                <input type="date" class="form-control" name="search_date1" id="search_date1" placeholder="결제일 시작" value="${responseDTO.search_date1}">
                <span class="input-group-text">~</span>
                <input type="date" class="form-control" name="search_date2" id="search_date2" placeholder="결제일 끝" value="${responseDTO.search_date2}">
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
                <button class="btn btn-outline-primary" type="submit" id="search_button">검색</button>
                <button class="btn btn-outline-primary" type="button" onclick="window.location.href='/study/mystudy'">검색 초기화</button>
            </div>
            <div class="d-flex">
                <button class="btn btn-outline-primary" type="button" name="search_like" onclick="location.href='/study/mystudy?search_like=like'"  >좋아요 순</button> &nbsp;
                <button class="btn btn-outline-primary" type="button" onclick="location.href='/study/mystudy'">등록일 순</button>
                <div class="ms-auto">
                <select name="page_size" id="page_size" class="form-select form-select-sm" style="width: 80px; height: 38px">
                    <option value="10">개수</option>
                    <option value="3" ${responseDTO.page_size == '3' ? 'selected' : ''}>3개</option>
                    <option value="5" ${responseDTO.page_size == '5' ? 'selected' : ''}>5개</option>
                    <option value="8" ${responseDTO.page_size == '8' ? 'selected' : ''}>8개</option>

                </select>

                </div>
            </div>
        </form>
    </div>

    <br>
    <table class="table">
        <thead>
        <tr class="table-secondary">
            <%--<th scope="col"></th>--%>
            <th scope="col">No</th>
            <th scope="col">제목</th>
            <th scope="col">등록일</th>
            <th scope="col">좋아요</th>
            <th scope="col">오늘의 학습<br> 노출여부 </th>
            <th scope="col">오늘의 학습<br> 노출기간</th>

        </tr>
        </thead>

        <form action="/study/today" method="post" id="frm" name="frm">
            <c:choose>
                <c:when test="${empty responseDTO.dtoList}">
                    <td colspan="6" style="text-align: center">  등록된 학습 게시물이 없습니다.</td>
                </c:when>
        <c:otherwise>

            <c:forEach var="list" items="${responseDTO.dtoList}" varStatus="status">
                <tbody >
                <tr>
                   <%-- <input type="hidden" name="payment_idx" value="${list.payment_idx}"/>
                    <input type="hidden" name="book_idx" value="${list.book_idx}"/>
                    <input type="hidden" name="product_quantity" value="${list.product_quantity}"/>--%>
                    <td> <a href="/study/view?no=${list.no}">  ${list.no} </a></td><%--${(status.count) + (10*(responseDTO.page-1))}--%>
                    <td>${list.title} </td> <%--<a href="/admin/apayment/view?payment_idx=${list.payment_idx}&book_idx=${list.book_idx}">${list.product_name} </a>--%>
                    <td>${list.reg_date}</td>

                    <td>${list.like } </td>
                    <td> ${list.status}</td>
                    <td>${list.exposure_start} ~ ${list.exposure_end} </td>

                </tr>
                </tbody>


            </c:forEach>
        </c:otherwise>

            </c:choose>
        </form>
    </table>
    <nav>
        <ul class="pagination justify-content-center">
            <li class="page-item
                                        <c:if test="${responseDTO.prev_page_flag ne true}"> disabled</c:if>">
                <!--a class="page-link" data-num="1" href="page=1">Previous</a-->
                <a class="page-link"
                   data-num="<c:choose><c:when test="${responseDTO.prev_page_flag}">${responseDTO.page_block_start-1}</c:when><c:otherwise>1</c:otherwise></c:choose>"
                   href="<c:choose><c:when test="${responseDTO.prev_page_flag}">${responseDTO.linkParams}&page=${responseDTO.page_block_start-10}</c:when><c:otherwise>#</c:otherwise></c:choose>">Previous</a>
            </li>
            <c:forEach begin="${responseDTO.page_block_start}"
                       end="${responseDTO.page_block_end}"
                       var="page_num">
                <li class="page-item<c:if test="${responseDTO.page == page_num}"> active</c:if> ">
                    <a class="page-link" data-num="${page_num}"
                       href="<c:choose><c:when test="${responseDTO.page == page_num}">#</c:when><c:otherwise>${responseDTO.linkParams}&page=${page_num}</c:otherwise></c:choose>">${page_num}</a>
                </li>
            </c:forEach>
            <li class="page-item<c:if test="${responseDTO.next_page_flag ne true}"> disabled</c:if>">
                <a class="page-link"
                   data-num="<c:choose><c:when test="${responseDTO.next_page_flag}">${responseDTO.page_block_end+1}</c:when><c:otherwise>${responseDTO.page_block_end}</c:otherwise></c:choose>"
                   href="<c:choose><c:when test="${responseDTO.next_page_flag}">${responseDTO.linkParams}&page=${responseDTO.page_block_end+1}</c:when><c:otherwise>#</c:otherwise></c:choose>">Next</a>
            </li>
        </ul>
    </nav>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-1">
        <button class="btn btn-outline-primary" type="button" onclick="location.href='/study/regist'">학습등록</button>
    </div>

</main>
<script>
    const search_button = document.querySelector("#search_button");
    search_button.addEventListener("click",function(e){
       e.preventDefault();


        const search_word = document.querySelector("#search_word").value;
        const search_type_0 = document.querySelector("#search_type_0")
        if (!search_type_0.checked) {
            alert('검색범위를 선택해주세요');
            return false;

        }
        if(search_word.length === 0){
            alert("검색어를 입력해주세요.");
            return false;
        }

        document.frmSearch.submit();
    });

    var selectBox = document.getElementById('page_size');
    selectBox.onchange = function() {
        // 선택된 값
        var selectedValue = this.value;


        // 페이지 이동
        window.location.href = '/study/mystudy?page_size=' + selectedValue;
    };
</script>

<%@ include file="../common/footer.jsp"%>
</body>
</html>
