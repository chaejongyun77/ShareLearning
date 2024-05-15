
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
                <button class="btn btn-outline-primary" type="submit">검색</button>
                <button class="btn btn-outline-primary" type="button" onclick="window.location.href='/study/mystudy'">검색 초기화</button>
            </div>
        </form>
    </div>
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

            <c:forEach var="list" items="${responseDTO.dtoList}" varStatus="status">
                <tbody >
                <tr>
                   <%-- <input type="hidden" name="payment_idx" value="${list.payment_idx}"/>
                    <input type="hidden" name="book_idx" value="${list.book_idx}"/>
                    <input type="hidden" name="product_quantity" value="${list.product_quantity}"/>--%>
                    <td>${list.no}</td><%--${(status.count) + (10*(responseDTO.page-1))}--%>
                    <td>${list.title} </td> <%--<a href="/admin/apayment/view?payment_idx=${list.payment_idx}&book_idx=${list.book_idx}">${list.product_name} </a>--%>
                    <td>${list.reg_date}</td>

                    <td>${list.like}</td>
                    <td> ${list.status}</td>
                    <td>${list.exposure_start} ~ ${list.exposure_end}</td>

                </tr>
                </tbody>
            </c:forEach>

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
        <button class="btn btn-outline-primary" type="submit">학습등록</button>
    </div>

</main>

<%@ include file="../common/footer.jsp"%>
</body>
</html>
