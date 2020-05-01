<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>

<div class="grid-wrapper">

    <div class="main-container">
        <h4>Pick source and destination</h4>

        <form onsubmit="submitForm(event);">
            <label for="source">Source</label>
            <div class="input-group mb-3"> <!-- creating src select dropdown with text -->
                <div class="input-group-prepend">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" id="titledrop">Select
                    </button> <!--dropdown button-->
                    <div class="dropdown-menu" id="src-menu"><!-- setting dropdown items-->
                        <%--                        this will be filled with js--%>

                    </div>
                </div>
                <input class="form-control" type="text" name="title" id="source" placeholder="Pick location" disabled>
            </div>

            <label for="destination">Destination</label>
            <div class="input-group mb-3"> <!-- creating dest select dropdown with text -->
                <div class="input-group-prepend">
                    <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" id="titledrop">Select
                    </button> <!--dropdown button-->
                    <div class="dropdown-menu" id="dst-menu"><!-- setting dropdown items-->
                        <%--                        this will be filled with js--%>
                    </div>
                </div>
                <input class="form-control" type="text" name="title" id="destination" placeholder="Pick location" disabled>
            </div>
            <button type="submit" name="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="js/script.js"></script>
</body>
</html>