<!DOCTYPE html>
<html>
<head>
    <title>Sorted Array</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
            margin-top: 50px;
        }
        .links a {
            margin: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Sorted Array</h1>
    <p>${sortedArray}</p>
    <p>Algorithm: ${algorithm}</p>
    <p>Execution Time: ${executionTime} nanoseconds</p>
    <p>Time Complexity: ${timeComplexity}</p>
    <p>Sort with a different sorting algorithm?</p>
    <div class="links">
        <a href="/AdvancedSortingWA/sort?array=${originalArray}&algorithm=quick" >Quick Sort</a>
        <a href="/AdvancedSortingWA/sort?array=${originalArray}&algorithm=merge" >Merge Sort</a>
        <a href="/AdvancedSortingWA/sort?array=${originalArray}&algorithm=bucket" >Bucket Sort</a>
        <a href="/AdvancedSortingWA/sort?array=${originalArray}&algorithm=radix" >Radix Sort</a>
        <a href="/AdvancedSortingWA/sort?array=${originalArray}&algorithm=heap" >Heap Sort</a>
    </div>
    <a href="${pageContext.request.contextPath}/" class="btn btn-secondary">Sort Another Array</a>
</div>
</body>
</html>
