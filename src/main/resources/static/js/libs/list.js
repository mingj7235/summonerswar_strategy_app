function re () {
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

function searchMonster() {
    $("#orderJobList tbody").show()
    commonUtil.loadingStart();
    table = $("#orderJobList").DataTable({
        processing: true,
        serverSide: true,
        ajax: {
            url: "/v1/monsters/search",
            data: function (d) {
                var otherOption = $("#otherOptions").val();
                var data = {
                    "brandCode": $("input[name=brandCode]:checked").val() === "ALL" ? null : $("input[name=brandCode]:checked").val(),
                    "payDate.from" : $("#payFromDate").val() +'T'+ $("#payFromTime").val()+ /*[[${fromSecond}]]*/,
                    "payDate.to" : $("#payToDate").val() +'T'+ $("#payToTime").val()+ /*[[${toSecond}]]*/,
                    "orderStatus": $("#orderStatus").val() === "ALL" ? null : $("#orderStatus").val(),
                    "deliveryType.deliveryCode": $("#deliveryCode").val() === "ALL" ? null : $("#deliveryCode").val(),
                    "deliveryType.deliveryCompany": $("#deliveryCompany").val() === "ALL" ? null : $("#deliveryCompany").val(),
                    "otherOptions.orderCode": otherOption === "orderCode" ? $("#orderOptionValue").val() : null,
                    "otherOptions.orderMail": otherOption === "orderMail" ? $("#orderOptionValue").val() : null,
                    "otherOptions.orderName": otherOption === "orderName" ? $("#orderOptionValue").val() : null,
                    "otherOptions.recipientName": otherOption === "recipientName" ? $("#orderOptionValue").val() : null,
                    "indexType.indexName": $("#indexName").val() === "ALL" ? null : $("#indexName").val(),
                    "indexType.workedYN": $("#workedYN").val() === "ALL" ? null : $("#workedYN").val(),
                    "orderType": $("#orderType").val() === "ALL" ? null : $("#orderType").val(),
                    "direction": d.order[0].dir.toUpperCase(),
                };

                return data;
            },
            async: true,
            type: "GET",
            dataFilter: function (data) {
                data = JSON.parse(data);
                var json = {
                    recordsTotal: data.responseObject.size,
                    data: data.responseObject
                };
                $("#searchListSize").text(data.responseObject.length)
                return JSON.stringify(json);
            }
        },
        dataSrc: "",
        // pageLength: 10,
        columns: [
            // { data: null },
            // { data: 'id',render: function (data, type, row, meta) {return meta.row + meta.settings._iDisplayStart + 1;}},
            { data: 'brandCode'}, // 서비스
            { data: 'orderType' }, // 구분
            { data: 'orgOrderCode' }, //주문 번호
            { data: 'orderMail' }, // 아이디
            { data: 'orderName' }, // 주문인
            { data: 'recipientName' }, // 수령인
            { data: 'deliveryCode' }, // 배송 유형
            { data: 'deliveryCompany'},
            { data: 'indexName', render: function (data, type, row, meta){

                    var result = "<div style='display: flex;'>";

                    if (data != null) {
                        var indexList = data.split(",");
                        var set = new Set(indexList)
                        var setIndexList = [...set];

                        for (let i = 0; i < setIndexList.length; i++) {

                            if (setIndexList[i].indexOf('_Y') > -1) {
                                result += "<button style=\"border: none\" class=\"badge badge-success-lighten\" type=\"button\">"+setIndexList[i].replace("_Y", "") + " </button>&nbsp";
                            } else {
                                result += "<button style=\"border: none\" class=\"badge badge-secondary-lighten\" type=\"button\">"+setIndexList[i].replace("_N", "") +"</button>&nbsp";
                            }
                        }
                    }
                    result += "</div>";
                    return result;
                }
            }, // 인덱스
            { data: 'payDate',render: function (data, type, row, meta){return commonUtil.dateFormat(data)} },
            { data: 'deliveryDueDate' }, // 배송 예정일
            { data: 'downStatus' }, // 상태
            { data: 'errorStatus' }, // 오류 요청 상태

        ],
        'paging' : false,
        'destroy': true,
        'searching': false,
        // 'columnDefs': [{
        //     'targets': 0,
        //     'searchable':false,
        //     'orderable':false,
        //     'className': 'dt-body-center',
        //     'render': function (data, type, full, meta){
        //         return '<input type="checkbox" name="id[]" value="'
        //             + meta.row + meta.settings._iDisplayStart + 1 + '">';
        //     }
        // }],
        "language": {
            // processing: '<div class="spinner-border" role="status"> <span class="visually-hidden">Loading...</span></div>',
            emptyTable : '<div>검색 결과가 없습니다.</div>',
            "paginate": {
                "previous": "<i class='mdi mdi-chevron-left'>",
                "next": "<i class='mdi mdi-chevron-right'>"
            }
        },
        "drawCallback": function (settings) {
            commonUtil.loadingStop()
            $('.dataTables_paginate > .pagination').addClass('pagination-rounded');
        },
        'order': [1, 'desc']

    });

    $('#select-all').on('click', function(){
        // Check/uncheck all checkboxes in the table
        var rows = table.rows({ 'search': 'applied' }).nodes();
        $('input[type="checkbox"]', rows).prop('checked', this.checked);
    });
    $("#orderJobList_info").hide()
}

function monsterListInit(){

    $("#monsterName").val('');
    $("#keyWord").val('');
    $("#attribute").val("ALL");
    $("#leaderSkill").val("ALL");

    $("#monsterList tbody").hide()

}