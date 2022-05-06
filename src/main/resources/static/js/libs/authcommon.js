
/**
 * <pre>
 * 1. FuntionName : auth
 * 2. ClassName  : authcommon.js
 * 3. Comment    : auth체크용 공통
 * 4. 작성자       : tony
 * 5. 작성일       : 2022.04.28
 * </pre>
 */

// $(document).ready(function() {
//
//     //첫 로그인이후 로컬스토리지에서 꺼내서 해더에 추가
//     var accessToken = localStorage.getItem("accessToken");
//     var refreshToken = localStorage.getItem("refreshToken");
//     $.ajax({
//         type: "POST",
//         url: "/admin/account/auth",
//         beforeSend: function (xhr) {
//             xhr.setRequestHeader("Authorization",accessToken);
//             xhr.setRequestHeader("REFRESH-Authorization",refreshToken);
//         },
//         success: function (res) {
//             if(res.result.resultCodet =! null && res.result.resultCode == "true"){
//                 location.href="/admin/account/login";
//             }else if(res.result.reAccessToken =! null && res.result.resultCode == "false" ){
//                 localStorage.setItem("accessToken",res.result.accessToken);
//                 localStorage.setItem("refreshToken",res.result.refreshToken);
//             }
//         }
//     });
//
// });
