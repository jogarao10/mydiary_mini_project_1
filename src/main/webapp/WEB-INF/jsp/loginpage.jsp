<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
<title>MyDiary home page</title>

<link rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>

	<div class="header">
		<div class="first"></div>
		<img src="<c:url value="/images/diary.jpg"/>" width="60" height="60">
		<div class="second">Mydiary App</div>
		
	</div>
	<br/><br/>
	<hr/>
	
	<div class="bodypart">
		<div class="bodypart1">
			<img src="<c:url value="/images/diary.jpg"/>">
		</div>
		<div class="bodypart2">
			<h1>LOGIN HERE</h1><br/><br/>
			<form action="authenticate" method="POST">
				<label>username <input type="text" name="username" class="formcontrol"></label><br/><br/>
				<label>password <input type="password" name="password" class="formcontrol"></label><br/><br/>
				<button type="submit">LOGIN</button>
			
			</form>
			<br/><br/>
			
		new user? <a href="./register">Register</a>	
		</div>
	</div>
	


</body>
</html>













