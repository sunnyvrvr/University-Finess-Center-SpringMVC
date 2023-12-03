$(() => {
  // 세션 없애기
  $.ajax({
    url: "/logout",
    method: "GET",
    success: () => {
    sessionStorage.clear(); 
    console.log("로그아웃 성공");
    alert("로그아웃 성공");
    location.href = "/index";
    },
    error: (xhr) => {
      alert("로그아웃 실패" + xhr.status);
    },
  });
});
