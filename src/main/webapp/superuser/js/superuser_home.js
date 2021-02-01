


document.addEventListener('DOMContentLoaded', function () {

    setTimeout(
        () => loadDetails(),
        2
    )

});


function loadDetails() {

    const xhr = new XMLHttpRequest();
    const url = "/api/superuser/home-page-info";

    xhr.open('GET', url, true);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            const data = JSON.parse(this.response);

            document.getElementById("TotalCustomerCount").innerHTML = data.customer_count;
            document.getElementById("TotalEmployeeCount").innerHTML = data.employee_count;
            document.getElementById("TotalOrdersCount").innerHTML = data.order_count;

            loadAdminCards(data.admin_list);
        }
    }

    xhr.send(null);

}

function loadAdminCards(admin_list) {

    const adminListCardElement = document.getElementById('AdminListCardDiv');

    admin_list.forEach(admin_detail => {

        let element = document.createElement('div');
        element.className = "col-md-6";

        let card = `
        <div class="box">
            <div class="admin">
                <div class="img">
                    <img class="img-responsive"
                        src="https://uniim1.shutterfly.com/ng/services/mediarender/THISLIFE/021036514417/media/23148906966/small/1501685402/enhance"
                        alt="admin">
                </div>
                <div class="info">
                    <h3>${admin_detail.name}</h3>
                    <p>${admin_detail.email}</p>
                    <p>${admin_detail.personal_contact}</p>
                </div>
            </div>
        </div>
                
        `;

        element.innerHTML = card;
        adminListCardElement.append(element);

    });

}