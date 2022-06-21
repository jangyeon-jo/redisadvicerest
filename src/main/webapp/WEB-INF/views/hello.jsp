<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>안녕하세요</title>
</head>
<body>
<script type="text/javascript">
    //jQuery의 ready함수는 document가 준비되는 시점에 실행된다.
    /* 	jQuery(document).ready(function() {
            alert("jquery");
        }); */
    // jQuery와 $는 같은 표현이다
    $(document).ready(function() {
        //alert("jquery"+ document.getElementById("ts"));

        //ready 시점에 현 페이지의 이벤트를 등록한다.
        // 이벤트 : a tag를 클릭했을 때, 자신의 텍스트를 alert
    });
</script>
<input type="button" id="listButton" value="리스트출력" />
<br/>
<div id="listDiv"></div>
</body>
</html>