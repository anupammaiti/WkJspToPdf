<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <script type="application/javascript" src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    </head>
    <body class="container">
    <h2>Welcome to the PDF Testing App</h2>
    <div id="content">
        <button id="pdfTest" class="btn btn-primary">PDF Testing Page</button>
    </div>
    </body>
    <script type="application/javascript">
        $(function() {
            $('#pdfTest').click(function(event) {
                window.location.href = '<c:url value="/pdf" ></c:url>'
            });
        });
    </script>
</html>