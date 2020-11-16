const search = {
    init: function () {
        var _this = this;
    }, // init end
    form: {
        send: function () {
            var form = $('#file-form')[0];
            var formData = new FormData(form);
            formData.append("file", $("#file")[0].files[0]);
            formData.append("imageId", 2);
            const config = {
                headers: {'content-type': 'multipart/form-data'}
            }
            return axios.post('http://localhost:8080/api/images', formData, config)
                .then(function (response) {
                    $("#table-body").html("");
                    let html = "";
                    response.data.list.map((value, index) => {
                        html += "<tr>" +
                            "<td><img src='/images/" + value.image_name + "' style='width: 100px; height: 100px;' /></td>" +
                            "<td>" + value.score + "</td>" +
                            "<td>" + value.image_name + "</td>" +
                            "</tr>";
                    });
                    $("#table-body").html(html);
                    console.log('response', response.data.list);
                });
        }
    }


};