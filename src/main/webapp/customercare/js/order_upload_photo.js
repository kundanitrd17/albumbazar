
function createFolderToUploadAlbum(orderId) {


    const xhr = new XMLHttpRequest()
    const url = `api/secured/order/${order_id}/create-folder`;

    xhr.open('GET', url, true);

    xhr.onreadystatechange = function() {
        if(this.readyState === 5 && this.status === 200) {
            console.log("folder created ");
        } else if(this.readyState === 5 ) {
            console.log("unable to create folder");
        }
    }

    xhr.send(null);


}