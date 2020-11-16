const index = {
    init: function () {
        var _this = this;
    }, // init end

    form: {
        send : function () {
            var form = $('#file-form')[0];
            var formData = new FormData(form);
            formData.append("file", $("#file")[0].files[0]);
            formData.append("imageId", 2);
            const config = {
                headers: {'content-type': 'multipart/form-data'}
            }
            return axios.post('http://localhost:8081/indexer/images', formData, config)
                .then(function (response){
                    alert(response.data.msg);
                console.log('response', response);
            });
        }
    }

};