<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
	<link rel="stylesheet" type="text/css" href="/bootstrap-3.3.7/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="/css/valid.css"/>
	<script src="/js/jquery-3.1.0.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="/js/test.js" type="text/javascript" charset="utf-8"></script>
</head>
<body style="background:#fff;">
	<#import "./macro/page.ftl" as pageUtil />
	<div class="container">
        <div class="hearder" style="padding:10px 0">
			<button class="btn btn-default">get</button>
			<div class="cont"></div>
        </div>
        <div class="content">
            <div class="tittle">
			<#list userList as user>
			${user.id} ----  ${user.content}<br/>
			</#list>
			<@pageUtil.pager class="pageclass" url="getUsers?pageSize=2&current=" paginationSize="pagination" totalPage=20 curPage=1 showPageNum=5/>
            </div>
        </div>
	</div>
</body>
</html>
