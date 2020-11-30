const opencv = {
    init: function () {
        var _this = this;
        this.list.search();
    }, // init end

    list: {
        search: function () {
            var image_name = $("#image_name").val();
            var data = {indexKey: "opencv", imageName : image_name}
            const config = {
                headers: {'content-type': 'application/json'}
            }
            return axios.get('http://localhost:8081/indexer/data', { params : data }, config)
                .then(function (response) {
                    $("#table-body").html("");
                    let html = "";
                    response.data.list.map((value, index) => {
                        html += "<tr>" +
                            "<td><a href='/images/" + value.image_name + "' target='_blank'><img src='/images/" + value.image_name + "' style='width: 100px; height: 100px;' onclick=''/></a></td>" +
                            "<td>" + value.image_name + "</td>" +
                            "<td><button class=\"form-control form-control-user\" onclick=\"opencv.list.delete(\'" + value.id + "\')\">삭제</button></td>" +
                            "</tr>";
                    });
                    $("#table-body").html(html);
                    console.log('response', response.data.list);
                });
        },
        delete: function (id) {
            var data = {indexKey: "opencv", documentId: id}
            const config = {
                headers: {'content-type': 'application/json'}
            }
            return axios.delete('http://localhost:8081/indexer/data',{ params : data } , config)
                .then(function (response) {
                    alert(response.data.msg);
                    console.log('response', response.data.list);
                });
        }
    }

};