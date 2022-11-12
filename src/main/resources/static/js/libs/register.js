function registerMonster(){

    var imageInput = $("#file")[0];
    var formData = new FormData();
    formData.append("file", imageInput.files[0]);

    var param = {
        "name": $("#name").val(),
        "attribute": $("#attribute").val(),
        "leaderSkill": $("#leaderSkill").val()
    };

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
            if(data != null){
                alert("등록성공하였습니다.");
                window.location.href = "/monsters"
            }
        },error : function (req, status, error) {
            alert("몬스터 등록에 실패했습니다.");
        }
    });
}

function registerDefDeck(){

    var param = {
        "deckName": $("#name").val(),
        "deckDescription": $("#defDeckDescription").val(),
        "leaderMonsterId": $("#leaderMonster").val(),
        "secondMonsterId": $("#secondMonster").val(),
        "thirdMonsterId": $("#thirdMonster").val(),
    };

    $.ajax({
        type: "POST",
        url: '/v1/defDecks',
        dataType: "text",
        data: param,
        success: function (response) {
            if (response === 'ok') {
                alert("방덱 등록에 성공하였습니다.");
                window.location.reload();
            } else {
                alert("방덱 등록에 실패했습니다.");
                window.location.reload();
            }
        }
    });
}