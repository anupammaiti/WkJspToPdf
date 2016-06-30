<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script type="application/javascript" src="https://code.jquery.com/jquery-2.2.3.min.js"></script>
    <title>PDF Download</title>
</head>
<body class="container">
<h3>PDF Download Page</h3>
    <div class="row">
        <form>
            <div class="col-xs-12 col-sm-6">
                <div class="form-group">
                    <label for="filenameInput">Enter Filename for PDF to Stream</label>
                    <input type="email" class="form-control" id="filenameInput" placeholder="Filename">
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <button id="download-pdf" class="btn btn-primary">Download PDF as Stream</button>
        </div>
    </div>
</body>
<script type="application/javascript">
    $(function() {
        $('#download-pdf').click(function(e) {
            e.preventDefault();
            window.location.href = '<c:url value="/pdf/stream/download" />' + '?filename=' + $('#filenameInput').val();
        });
    });
</script>
</html>