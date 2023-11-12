<!doctype html>
<html lang="en" data-bs-theme="${mode}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  <title>Page 1 | Session App</title>
</head>

<body class="d-flex flex-column vh-100">
    <%@ include file="WEB-INF/partial/header.jsp" %>
    <main class="flex-grow-1">
        <h1 class="p-2 text-center">Page 1</h1>
        <p class="p-2 text-center">Logged : ${fullName}</p>
    </main>
    <%@ include file="WEB-INF/partial/footer.jsp" %>
</body>
</html>