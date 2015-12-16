<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>1号公交</title>

<link rel="stylesheet" href="../css/bootstrap.css" type="text/css"
	media="screen" title="no title">

<link rel="stylesheet" href="../css/reset.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/text.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/form.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/buttons.css" type="text/css"
	media="screen" title="no title" />
<!--内容布局-->
<link rel="stylesheet" href="../css/grid.css" type="text/css"
	media="screen" title="no title" />
<!--网页头部-->
<link rel="stylesheet" href="../css/layout.css" type="text/css"
	media="screen" title="no title" />

<!--图标-->
<link rel="stylesheet"
	href="../css/ui-darkness/jquery-ui-1.8.12.custom.css" type="text/css"
	media="screen" title="no title" />

<link rel="stylesheet" href="../css/plugin/jquery.visualize.css"
	type="text/css" media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/facebox.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/uniform.default.css"
	type="text/css" media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/dataTables.css"
	type="text/css" media="screen" title="no title" />
<!--线条-->
<link rel="stylesheet" href="../css/custom.css" type="text/css"
	media="screen" title="no title">
<!-- #图标 -->
<link rel="shortcut icon" href="../images/bus.png" type="image/x-icon" />
<script type="text/javascript">
	function queren(number) {
		var se = confirm("你确定删除工号为:"+number+"的管理员？");
		if (se == true) {	
			return true;
		} else {
			return false;
		}
	}
	
	function editSuccess()
	{
		if("${sEM}"=="editSuccess")
		{
			alter("管理员信息修改成功!");
		}
		if("${sAM}"=="addSuccess")
		{
			alter("添加管理员成功!");
		}
	}
</script>


</head>

<body onload="editSuccess()">

	<div id="wrapper">


		<div id="header">

			<div class="content_pad">
				<div style="position: absolute; top: 30px; left: 0px">

					<a  ><img src="../images/onebus.png" width="60%"></a>

				</div>

				<ul id="nav">
					<li class="nav_current nav_icon"><a><span
							class="ui-icon ui-icon-home"></span>管理员信息</a></li>

					<li class="nav_dropdown nav_icon"><a href="/onebus/main/index"><span
							class="ui-icon ui-icon-person"></span>注销</a></li>
				</ul>
			</div>
			<!-- .content_pad -->

		</div>
		<!-- #header -->

		<div id="masthead">

			<div class="content_pad">

				<h1 class="">管理员信息</h1>
				<!-- #bread_crumbs -->


			</div>
			<!-- .content_pad -->

		</div>
		<!-- #masthead -->

		<div id="content" class="xgrid">


			<h3 align="right">
				<a href="/onebus/main/addManager">添加新管理员</a>
			</h3>

			<div class="accordion_container">





				<div align="center">

					<p style="color: red; font-size: 18px; font-weight: bold;">
						<span id="spanFirst">第一页</span> <span id="spanPre">上一页</span> <span
							id="spanNext">下一页</span> <span id="spanLast">最后一页</span> 第<span
							id="spanPageNum"></span>页/共<span id="spanTotalPage"></span>页
					</p>

					<table border="2" id="table1" align="center"
						class="table table-bordered">
						<tr class="active">
							<th>工号</th>
							<th>用户名</th>						
							<th>邮箱</th>
							<th>电话</th>
							<th>地址</th>
							<th>删除</th>
							<th>编辑</th>

						</tr>
						<tbody id="table2">
							<c:forEach var="manager" items="${managers}">
								<tr class="success">
									<td>${manager.getNumber()}</td>
									<td>${manager.getUsername()}</td>
									<td>${manager.getEmail()}</td>
									<td>${manager.getPhone()}</td>
									<td>${manager.getAddress()}</td>
									<td><a
										href="/onebus/main/deleteManager?number=${manager.getNumber()}"
										onclick="return queren(${manager.getNumber()})"><img
											style="display: block; width: 25%; align: center;"
											src="../images/map_delete.png" /></a></td>
									<td><a
										href="/onebus/main/editManager?number=${manager.getNumber()}"><img
											style="display: block; width: 10%;" src="../images/write.png" /></a></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>







			</div>
			<!-- .accordion_container -->



		</div>
		<!-- #content -->

		<div id="footer">
			<div class="content_pad">
				<p>&copy; 2014-15 Copyright MadeBy MavelCo</p>
			</div>
			<!-- .content_pad -->
		</div>
		<!-- #footer -->

	</div>
	<!-- #wrapper -->



</body>

</html>


<script>
     var theTable = document.getElementById("table2");
     var totalPage = document.getElementById("spanTotalPage");
     var pageNum = document.getElementById("spanPageNum");


     var spanPre = document.getElementById("spanPre");
     var spanNext = document.getElementById("spanNext");
     var spanFirst = document.getElementById("spanFirst");
     var spanLast = document.getElementById("spanLast");


     var numberRowsInTable = theTable.rows.length;
     var pageSize = 15;
     var page = 1;


     //下一页
     function next() {


         hideTable();


         currentRow = pageSize * page;
         maxRow = currentRow + pageSize;
         if (maxRow > numberRowsInTable) maxRow = numberRowsInTable;
         for (var i = currentRow; i < maxRow; i++) {
             theTable.rows[i].style.display = '';
         }
         page++;


         if (maxRow == numberRowsInTable) { nextText(); lastText(); }
         showPage();
         preLink();
         firstLink();
     }


     //上一页
     function pre() {


         hideTable();


         page--;


         currentRow = pageSize * page;
         maxRow = currentRow - pageSize;
         if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;
         for (var i = maxRow; i < currentRow; i++) {
             theTable.rows[i].style.display = '';
         }




         if (maxRow == 0) { preText(); firstText(); }
         showPage();
         nextLink();
         lastLink();
     }


     //第一页
     function first() {
         hideTable();
         page = 1;
         for (var i = 0; i < pageSize; i++) {
             theTable.rows[i].style.display = '';
         }
         showPage();


         preText();
         nextLink();
         lastLink();
     }


     //最后一页
     function last() {
         hideTable();
         page = pageCount();
         currentRow = pageSize * (page - 1);
         for (var i = currentRow; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = '';
         }
         showPage();


         preLink();
         nextText();
         firstLink();
     }


     function hideTable() {
         for (var i = 0; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = 'none';
         }
     }


     function showPage() {
         pageNum.innerHTML = page;
     }


     //总共页数
     function pageCount() {
         var count = 0;
         if (numberRowsInTable % pageSize != 0) count = 1;
         return parseInt(numberRowsInTable / pageSize) + count;
     }


     //显示链接
     function preLink() { spanPre.innerHTML = "<a href='javascript:pre();'>上一页</a>"; }
     function preText() { spanPre.innerHTML = "上一页"; }


     function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>下一页</a>"; }
     function nextText() { spanNext.innerHTML = "下一页"; }


     function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>第一页</a>"; }
     function firstText() { spanFirst.innerHTML = "第一页"; }


     function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>最后一页</a>"; }
     function lastText() { spanLast.innerHTML = "最后一页"; }


     //隐藏表格
     function hide() {
         for (var i = pageSize; i < numberRowsInTable; i++) {
             theTable.rows[i].style.display = 'none';
         }


         totalPage.innerHTML = pageCount();
         pageNum.innerHTML = '1';


         nextLink();
         lastLink();
     }


     hide();
</script>