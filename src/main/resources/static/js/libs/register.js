function registerMonster(){

    var imageInput = $("#file")[0];
    var formData = new FormData();
    formData.append("file", imageInput.files[0]);

    var param = {
        "name": $("#name").val(),
        "attribute": $("#attribute").val(),
        "leaderSkill": $("#leaderSkill").val()
    };

    console.log(param);

    formData.append("request", new Blob([JSON.stringify(param)], {type: "application/json"}));

    $.ajax({
        type: "POST",
        url: '/v1/monsters',
        data: formData,
        processData: false,
        contentType: false,
        cache: false,
        success: function (response) {
            var data = response;
            console.log(data);
            if(data != null){
                alert("등록성공하였습니다.");
                window.location.href = "/monsters"
            }
        },error : function (req, status, error) {
            alert("몬스터 등록에 실패했습니다.");
        }
    });

}