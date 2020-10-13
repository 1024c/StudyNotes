<%--
  页面部分单独提取, 使用时需要先指定当前页参数: currentPage
  <c:set var="currentPage" value="admin"/>

  Project: 众筹网模板
  User: Administrator
  Date: 10/9/2020
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<tfoot>
<tr>
    <td colspan="6" align="center">
        <ul class="pagination">
            <c:if test="${pageInfo.isFirstPage}">
                <li class="disabled"><a href="javascript:void(0)">首页</a></li>
                <li class="disabled"><a href="javascript:void(0)">上一页</a></li>
            </c:if>
            <c:if test="${!pageInfo.isFirstPage}">
                <li><a href="${currentPage}/page.html?pageNum=1&keyword=${param.keyword}">首页</a></li>
                <li>
                    <a href="${currentPage}/page.html?pageNum=${pageInfo.prePage}&keyword=${param.keyword}">上一页</a>
                </li>
            </c:if>
            <c:forEach items="${pageInfo.navigatepageNums}" var="pageNum">
                <c:if test="${pageNum == pageInfo.pageNum}">
                    <li class="active"><a href="javascript:void(0)">${pageNum}<span
                            class="sr-only">(current)</span></a></li>
                </c:if>
                <c:if test="${pageNum != pageInfo.pageNum}">
                    <li>
                        <a href="${currentPage}/page.html?pageNum=${pageNum}&keyword=${param.keyword}">${pageNum}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${pageInfo.isLastPage}">
                <li class="disabled"><a href="javascript:void(0)">下一页</a></li>
                <li class="disabled"><a href="javascript:void(0)">末页</a></li>
            </c:if>
            <c:if test="${!pageInfo.isLastPage}">
                <li>
                    <a href="${currentPage}/page.html?pageNum=${pageInfo.nextPage}&keyword=${param.keyword}">下一页</a>
                </li>
                <li>
                    <a href="${currentPage}/page.html?pageNum=${pageInfo.pages}&keyword=${param.keyword}">末页</a>
                </li>
            </c:if>
        </ul>
    </td>
</tr>
</tfoot>