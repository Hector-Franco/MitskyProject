<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<title>Events</title>
</head>
<body>

	<table class="table table-striped table-danger">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><span class="badge badge-pill badge-danger">${item.id}</span></td>
					<td>${item.name}</td>
					<td>${item.eventDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
	integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
	crossorigin="anonymous"></script>

</body>


</html>