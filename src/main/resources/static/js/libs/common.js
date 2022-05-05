/**
 * <pre>
 * 1. FuntionName : MsgBox
 * 2. ClassName  : common.js
 * 3. Comment    : 부트스트랩 공통 메세지 박스 유틸
 * 4. 작성자       : tony
 * 5. 작성일       : 2022.04.13
 * </pre>
 */


var  MsgBox = {
    /**
     * Dialog Id
     */
    DialogInfo: {
        titleId: 'common-modal-title',
        okBtnId: 'common-modal-ok-btn',
    },

    /**
     * Default Item Message
     */
    Message: {
        title: '알림',
        ok: '확인',
        cancel: '취소'
    },

    /**
     * Element 생성
     *
     * @param elementTag Element Tag
     * @param attributeData Set Attribute Object
     * @param appendElement AppendChild Element Object
     * @return Element Object
     */
    createElement: function(elementTag, attributeData, appendElement){
        // Element 생성
        var elementObj = document.createElement(elementTag);
        // Attrbute 추가
        for(var key in attributeData){
            //console.log(key, attrbutes[key]);
            elementObj.setAttribute(key, attributeData[key]);
        };
        // Element Append
        if(appendElement){
            appendElement.appendChild(elementObj);
        }

        return elementObj;
    },
    /**
     * Modal Dialog Make
     */
    makeModalDialog: function(data){
        // UUID 생성
        var uuid;
        if(typeof(Util) === 'object' && typeof(Util.uuid) === 'function'){
            uuid = Util.uuid();
        }else{
            uuid = 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
                return v.toString(16);
            });
        }

        // Dialog Id
        data.dialogId = 'common-modal-dialog-' + data.dialogType + '-' + uuid;

        /***************************************************************************/
        // ---------------- Modal Main Div ----------------
        var modalDiv = this.createElement('div'
            , {'id': data.dialogId,'class': 'modal fase','tabindex': '-1','role': 'dialog','aria-labelledby': this.DialogInfo.titleId,'aria-hidden': 'true'}
            , document.body);
        var modalDialogDiv = this.createElement('div', {'class': 'modal-dialog'}, modalDiv);
        var modalContentDiv = this.createElement('div', {'class': 'modal-content'}, modalDialogDiv);

        // ---------------- Modal Header ----------------
        var modalHeaderDiv = this.createElement('div', {'class': 'modal-header'}, modalContentDiv);

        // Close Header
        if(data.closeBtn){
            var modalHeaderCloseBtn = this.createElement('button', {'type': 'button', 'class': 'close', 'data-dismiss': 'modal', 'aria-hidden': 'true'}, modalHeaderDiv);
            modalHeaderCloseBtn.innerHTML ='x';
        }

        // title
        var modalHeaderTitle = this.createElement('h4', {'class': 'modal-title', 'id': this.DialogInfo.titleId}, modalHeaderDiv);
        modalHeaderTitle.innerHTML = data.title ? data.title : this.Message.title;

        // ---------------- Modal Body ----------------
        var modalBodyDiv = this.createElement('div', {'class': 'modal-body', 'style': 'word-break: break-all'}, modalContentDiv);;
        modalBodyDiv.innerHTML = data.content ? data.content : '';

        // ---------------- Modal Footer ----------------
        var modalFooterDiv = this.createElement('div', {'class': 'modal-footer'}, modalContentDiv);
        // Cancle Button
        if(data.dialogType == 'confirm'){
            var modalFooterCancleBtn = this.createElement('button', {'type': 'button', 'class': 'btn btn-default', 'data-dismiss': 'modal'}, modalFooterDiv);
            modalFooterCancleBtn.innerHTML = this.Message.cancel;
        }
        // Ok Button
        var modalFooterOkAttr = {'type': 'button', 'class': 'btn btn-primary', 'id': this.DialogInfo.okBtnId};
        if(data.dialogType == 'alert'){
            modalFooterOkAttr['data-dismiss'] = 'modal';
        }
        var modalFooterOkBtn = this.createElement('button', modalFooterOkAttr, modalFooterDiv);
        modalFooterOkBtn.innerHTML = this.Message.ok;
        /***************************************************************************/
        // Modal Default option Setting
        var modalOption = {
            keyboard: false // ESC 키 눌렀을때 모달을 닫음
            ,backdrop: 'static' // 모달의 배경을 포함. 클릭 시 모달을 닫지 않을 시 'static'
            ,show: false // 초기화 시 모달을 보여줍니다.
        }
        // 사용자 지정 정보 설정
        $.extend(modalOption, data);

        // Jquery Dialog 변수 선언
        var dialog = $('#' + data.dialogId);

        // Bootstrap Modal Setting
        dialog.modal(modalOption)

        // Modal의 hide가 완료된 이후 Event 실행
        dialog.on('hidden.bs.modal', function(e){
            // Modal Element 제거
            dialog.remove();
        });

        // Confirm 일 경우 확인 버튼 클릭 이벤트 지정
        if(data.dialogType == 'confirm'){
            dialog.find(" #" + this.DialogInfo.okBtnId).on('click', function(){
                // ok button event function 실행
                if(data.ok && typeof(data.ok) === 'function'){
                    data.ok();
                }else{
                    console.warn('not function ok event. dialogId: ' + data.dialogId);
                }

                // Modal 창 닫기
                dialog.modal('hide');
                // click enevt 제거
                $(this).off('click');
            });
        }

        // Modal Dialog Show
        dialog.modal('show');
    },

    /**
     * Modal Dialog Show
     */
    show: function(type, data){
        if(!data){data = {};}
        data.dialogType = type ? type : 'alert';
        this.makeModalDialog(data);
    },

    /**
     * Alert Modal Dialog
     *
     * data:
     *  title: Dialog Title Message
     *  content: Dialog Content Message
     *  closeBtn: Header Close Btn(x)
     *
     */
    alert: function(data){
        // data가 Object 가 아닌 경우 content 로 설정한다.
        if(typeof(data) != 'object'){
            var content = data;
            data = {content: content};
        }
        this.show('alert', data);
    },

    /**
     * Confirm Modal Dialog
     *
     * data:
     *  title: Dialog Title Message
     *  content: Dialog Content Message
     *  ok: Ok Button Click Event function
     *  closeBtn: Header Close Btn(x)
     *
     */
    confirm: function(data){
        this.show('confirm', data);
    }
};


/**
 * <pre>
 * 1. FuntionName : commonUtil
 * 2. ClassName  : common.js
 * 3. Comment    : 각종 공통으로 쓰는 모듈 나열
 * 4. 작성자       : tony
 * 5. 작성일       : 2022.04.13
 * </pre>
 */


var commonUtil ={
    getParameterByName: function(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search); return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
}


