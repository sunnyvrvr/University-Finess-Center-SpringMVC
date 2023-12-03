/*로그아웃 버튼, 로그인 버튼 처리 */
$(() => {
  const loginId = sessionStorage.getItem("loginId");
  // 로그아웃 버튼 참조
  const logoutBtn = document.getElementById("logoutBtn");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");

  // loginId가 세션스토리지에 없다면 로그아웃 버튼 숨기기
  if (!loginId) {
    //
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
  } else {
    logoutBtn.style.display = "block";
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
  }
  
  $("#health").click((e) => {
  	e.preventDefault();
  	location.href="/health";
  });    
});
