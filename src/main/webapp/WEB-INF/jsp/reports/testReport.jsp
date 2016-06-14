<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <style type="text/css">
        @font-face {
            font-family: "wqy";
            src: url(https://s3-us-west-2.amazonaws.com/fifetechs3/font/wqy.ttf) format("truetype");
        }
        h1 { font-family: "wqy"}
        tr {
            page-break-inside: avoid;
        }
        .page-break:last-child {
            page-break-after: auto;
        }
        .page-break {
            page-break-after: always;
        }
    </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: 'http://localhost:8080/pdf/data',
                type: 'GET',
                dataType: 'json',
                success: function (response) {
                    $('#container').highcharts({
                        chart: {
                            type: 'bar',
                            animation: false
                        },
                        plotOptions: {
                            line: {
                                animation: false
                            },
                            series: {
                                animation: false
                            }
                        },
                        title: {
                            text: 'Fruit Consumption'
                        },
                        xAxis: {
                            categories: [response.name, 'Bananas', 'Oranges']
                        },
                        yAxis: {
                            title: {
                                text: 'Fruit eaten'
                            }
                        },
                        series: [{
                            name: 'Jane',
                            data: [1, 0, 4]
                        }, {
                            name: 'John',
                            data: [5, 7, 3]
                        }]
                    });
                },
                error: function(err) {
                    $('#container').text(JSON.stringify(err));
                }
            });
        });
    </script>
</head>
<body>
<h1>正體字/繁體字</h1>
<div id="container">
    Chart Content
</div>
<table class="page-break">
    <thead>
    <tr>
        <th>Name</th>
        <th>Age</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${rows}" var="row">
        <tr>
            <td><c:out value="${row.name}"/></td>
            <td><c:out value="${row.age}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
