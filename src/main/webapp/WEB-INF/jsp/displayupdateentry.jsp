<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<img src="<c:url value="/images/diary.jpg"/>" width="60"
			height="60">
		<div class="second">Mydiary App</div>

	</div>
	<br />
	<br />
	<hr />
	<h2>
		welcome <span style="color: SteelBlue">${user.username}</span>
		<a href="./signout" style="color:red; float:right">Signout</a>
	</h2>
	<div class="bodypart">
		<div class="bodypart1">
			<img src="<c:url value="/images/diary.jpg"/>">
		</div>
		<div class="bodypart2">
			<h1>UPDATE ENTRY</h1>
			<br /> <br />
			<form action="./processentryupdate" method="POST">
				<label>Date<input type="date" name="entrydate"
					class="formcontrol" value="<fmt:formatDate value="${entry.entrydate}" pattern="dd/MM/yyyy"/>" readonly="readonly"></label><br /> <br /> 
					
					<label>description</label>
				<textarea rows="10" cols="30" name="description">
					 ${entry.description}
				</textarea>

				<input type="hidden" name="userid" value="${user.id}"> <br />
				<input type="hidden" name="id" value="${entry.id}"> <br />
				<br />
				<button type="submit">UPDATE ENTRY</button>

			</form>

		</div>

	</div>
</body>
</html>













