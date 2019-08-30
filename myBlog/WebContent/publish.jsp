<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
       background-color: #AEDD81;
    }
.text{
      background-color: #00CCFF;
}
   
</style>
</head>
<body>
当前位置:博文编辑
<form action="handlePublish.jsp" method="post">
博文作者:<input class="text" type="text" name="author"><br>
博文标题:<input class="text" type="text" name="title"><br>
博文分类:<select class="text" name="category">
        <option value="life">生活</option>
        <option value="emotion">情感</option> 
        <option value="constellation">星座</option>  
        <option value="technique">技术</option>  
         </select><br>
博文内容:<br>
<textarea class="text" name="content" rows="10" cols="100"></textarea><br>
<input type="submit" value="提交">
</form>

</body>
</html>