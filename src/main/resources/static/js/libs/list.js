function searchMonster () {
    $("#monsterList").show()
    let param = {
        monsterName: $("#monsterName").val(),
        // keyWord: $("#keyWord").val(),
        attribute: $("#attribute").val() === "ALL" ? null : $("#attribute").val(),
        leaderSkill: $("#leaderSkill").val() === "ALL" ? null : $("#leaderSkill").val()
    }

    $.ajax({
        type: "POST",
        url: "/v1/monsters/search",
        dataType: "json",
        async: false,
        data: param,
        success: function (response) {
            console.log('size: ' + response.length)

            let monsterListLength = response.length;
            let monsterList = response;
            let tag = '';

            if (monsterList.length > 0) {
                $.each(monsterList, function (i) {
                    tag += '<tr><td><img style="width: 50px; height: 50px" src="' + monsterList[i].photoPath + '" th:alt="No image"/>' +
                        '</td><td>' + monsterList[i].name + '</td>'

                    if (monsterList[i].attribute === "FIRE") {
                        tag += '<td> <span style=\"border: none\" class=\"badge badge-danger\">' +monsterList[i].attribute + '</span></td>';
                    } else if (monsterList[i].attribute === "WATER") {
                        tag += '<td> <span style=\"border: none\" class=\"badge badge-primary\">' +monsterList[i].attribute + '</span></td>';
                    } else if (monsterList[i].attribute === "WIND") {
                        tag += '<td> <span style=\"border: none\" class=\"badge badge-warning\">' +monsterList[i].attribute + '</span></td>';
                    } else if (monsterList[i].attribute === "LIGHT") {
                        tag += '<td> <span style=\"border: none\" class=\"badge badge-light\">' +monsterList[i].attribute + '</span></td>';
                    } else {
                        tag += '<td> <span style=\"border: none\" class=\"badge badge-dark\">' +monsterList[i].attribute + '</span></td>';
                    }

                    tag += '<td>' + monsterList[i].leaderSkill + '</td>';
                    tag += '</tr><br>'
                });
            }

            if (monsterList.length === 0) {
                console.log("길이가 0임")
                tag += '<div>검색 결과가 없습니다.</div>'
            }

            $("#searchListSize").text(monsterListLength)

            $("#monsterList").empty().append(tag)

        }
    });
}

function monsterListInit(){

    $("#monsterName").val('');
    // $("#keyWord").val('');
    $("#attribute").val("ALL");
    $("#leaderSkill").val("ALL");

    $("#monsterList").hide()

}