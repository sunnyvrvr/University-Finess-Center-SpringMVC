$(() => {
  /*세션 로그아웃, 마이페이지 버튼, 로그인 버튼 처리 */
  const loginM = sessionStorage.getItem("member");
  //const loginId = sessionStorage.getItem("loginId");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");
  // 로그아웃 버튼 참조
  const logoutBtn = document.getElementById("logoutBtn");
  // 마이페이지 버튼 참조
 // const myPageBtn = document.getElementById("myPageBtn");

  if (!loginM) {
    //세션스토리지 존재x-로그아웃, 마이페이지 버튼 숨김
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
  //  myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
  }

      
  const formObj = $("form.login");
  formObj.submit((e) => {
    e.preventDefault();

    $.ajax({
      url: "/login",
      method: "post",
      data: formObj.serialize(), // 폼 내용 직렬화
      success: (responseData) => {
        console.log(responseData, "로그인폼");
        if (responseData === "") {
          alert("로그인실패");
        } else {
          alert("로그인성공");
          sessionStorage.setItem("member", responseData);
          location.href = "./index"; //페이지 이동
        }
      },
      error: function(xhr){
        alert("상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText);
    },
      
    });
  });
});
