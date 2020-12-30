<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html lang="en">


    <meta name="_csrf" content="${_csrf.token}" />
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <!-- ... -->

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>

    <body>

        <div id="root">
            <button id="addCustomer">Add customer</button>

            <div class="users-container" style="display: none;">

                <c:forEach items="${users}" var="user">

                    <div id="customerBody${user.id}">
                        ${user.name}
                        <button onclick="lvlFun('${user.id}')">add</button>
                        <button onclick="deleteUser('${user.id}')">delete</button>

                        <div id="level1_id${user.id}" style="display: none;">
                            ${user.name}
                        </div>
                    </div>



                </c:forEach>





            </div>

        </div>


        <script>


            var header = document.querySelector("meta[name='_csrf_header']").content;//.attr("content");
            var token = document.querySelector("meta[name='_csrf']").content;//.attr("content");




            function deleteUser(id) {
                // Ahax to server
                console.log(id);

                var xhr = new XMLHttpRequest();
                var url = 'http://localhost:8080/test/delete';
                xhr.open("DELETE", url, true);
                xhr.setRequestHeader('Content-type', 'application/json');
                xhr.setRequestHeader(header, token);

                xhr.onreadystatechange = function () { // Call a function when the state changes.
                    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                        console.log("DOne Delete");
                        document.getElementById("customerBody" + id).style.display = "none";

                    }
                }
                xhr.send(JSON.stringify(id))
            }

            function lvlFun(id) {
                document.getElementById("level1_id" + id).style.display = "block";
            }

            document.getElementById("addCustomer").addEventListener('click', () => {

                document.querySelector(".users-container").style.display = "block";





            })



        </script>


    </body>

    </html>