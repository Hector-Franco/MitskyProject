<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">
<title>Clubs</title>
</head>
<body>

<!-- LIST ALL USERS -->
	<table class="table table-striped table-danger">
		<tr>
			<th scope="col">ID</th>
			<th scope="col">Name</th>
			<th scope="col">address</th>
			<th scope="col">Rate</th>
			<th scope="col">Music</th>
		</tr>
		
		<c:forEach items="${list}" var="item">
			<tr>
				<td><span class="badge badge-pill badge-danger bg-danger">${item.id}</span></td>				
				<td>${item.name}</td>
				<td>${item.address}</td>
				<td>${item.rate}</td>
				<td>${item.kindOfMusic}</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>