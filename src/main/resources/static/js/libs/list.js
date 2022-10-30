function searchMonster() {
    let param = {
    leaderMonster: $("#leaderMonster").val(),
    keyword: $("#keyword").val(),
    }

    let page = $("#page").val();
    let size = $("#size").val();

    $.ajax({
        type: "POST",
        url: "/def/findByKeyword?page=" + page + "&size=" + size,
        dataType: "json",
        async: false,
        data: param,
        success: function (response) {
        let monsterList = response.content;
        let totalPage = response.totalPages;
        let totalElements = response.totalElements;
        let resultNumber = response.number + 1;
        let resultSize = response.size;
    
        let listLength = monsterList.length;
    
        console.log(response)
    
        let tag = '';
        if (listLength > 0) {
            $.each(monsterList, function (i) {
                tag += '<tr><td>' + ((totalElements - (resultSize * (resultNumber - 1))) - i) +
                '</td><td>' + monsterList[i].leaderMonster +
                '</td><td>' + monsterList[i].otherMonster +
                '</td><td>' + monsterList[i].detailInfo +
                '</td><td>' + '<button><a href="/def/modify/' + monsterList[i].adminId + '">수정</a></button>'+ '</td>';
                tag += '</tr><br>'
            });
            tag += '<tr><td> total page : ' + totalPage + '</td></tr>'
            tag += '<tr><td> total elementes : ' + totalElements + '</td></tr>'
        }
    
        if (listLength === 0) {
        console.log("길이가 0임")
        tag += '<div>검색 결과가 없습니다.</div>'
    }
    
        $("#monsterList").empty().append(tag)
    }
    });
}