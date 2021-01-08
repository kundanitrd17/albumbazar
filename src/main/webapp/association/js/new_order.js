

function acceptOrderByAssociation(order_id) {

    const xhr = new XMLHttpRequest();

    const url = "http://localhost:8080/api/secured/association/order/accept";
    xhr.open('PUT', url, true);

    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader(header, token);


    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log("done");
            document.getElementById("orderRow" + order_id).remove();
        }
    }

    if (order_id == null) {
        return false;
    }

    xhr.send(JSON.stringify(order_id));


}


// function showDescriptionCard(id) {
//     const card = document.getElementById('DescriptionCard' + id);
//     var isHidden = card.hidden;
//     card.hidden = !isHidden;
// }