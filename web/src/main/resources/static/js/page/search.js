const search = {
    init: function () {

        var _this = this;

    }, // init end

    form: {

        send : function () {

            var form = $('#file-form')[0];
            var formData = new FormData(form);
            formData.append("file", $("#file")[0].files[0]);

            // $.ajax({
            //     url: 'http://localhost:8081/indexer/images',
            //     processData: false,
            //     contentType: false,
            //     data: formData,
            //     type: 'POST',
            //     success: function(result){
            //         alert("업로드 성공!!");
            //     }
            // });

            formData.append("imageId", 2);
            const config = {
                headers: {'content-type': 'multipart/form-data'}
            }
            return axios.post('http://localhost:8080/api/images', formData, config)
                .then(function (response){
                console.log('response', response);
            });
        }
    }



};