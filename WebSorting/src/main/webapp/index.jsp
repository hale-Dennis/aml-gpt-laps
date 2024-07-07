<!DOCTYPE html>
<html>
<head>
    <title>Sort Array</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>

        body {
            background-color: white;
            padding-top: 150px;
        }
        .container {
            max-width: 600px;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .btn-custom {
            background-color: black;
            color: white;
        }
        .form-control-custom {
            border-color: yellow;
        }

        .form-control-custom:focus {
            border-color: yellow;
            box-shadow: 0 0 5px rgba(255, 255, 0, 0.5);
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="text-center">DSA - SORTING ALGORITHMS</h1>
    <form action="sort" method="post">
        <div class="form-group">
            <label for="arrayInput">Enter an Array to Sort</label>
            <input type="text" class="form-control form-control-custom" id="arrayInput" name="array" placeholder="e.g., 1,2,3,4,5" required>
        </div>
        <div class="form-group">
            <label for="algorithmSelect">Select Sorting Algorithm</label>
            <select class="form-control form-control-custom" id="algorithmSelect" name="algorithm">
                <option value="quick">Quick Sort</option>
                <option value="merge">Merge Sort</option>
                <option value="bucket">Bucket Sort</option>
                <option value="radix">Radix Sort</option>
                <option value="heap">Heap Sort</option>
            </select>
        </div>
        <button type="submit" class="btn btn-custom btn-block">Sort</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</script>
</body>
</html>
