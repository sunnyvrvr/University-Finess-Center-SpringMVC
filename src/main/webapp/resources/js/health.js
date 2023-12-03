$(() => {
  /*세션 로그아웃, 마이페이지 버튼, 로그인 버튼 처리 */
  const loginM = sessionStorage.getItem("member");
  //const loginId = sessionStorage.getItem("loginId");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");
  // 로그아웃 버튼 참조
  const logoutBtn = document.getElementById("logoutBtn");
  // 마이페이지 버튼 참조
  //const myPageBtn = document.getElementById("myPageBtn");
  // 회원가입 버튼 참조
  const signupBtn = document.getElementById("signupBtn");

  if (!loginM) {
    //세션스토리지 존재x-로그아웃, 마이페이지 버튼 숨김
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
    //myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인,회원가입 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
    signupBtn.style.display = "none"; //세션스토리지 존재-회원가입버튼 숨김
  }
  /*프로그램 분류별로 프로그램 확인하기*/
  $.ajax({
    url: "/healthdata",
    type: "get",
    dataType: "json",
    success: (responseData) => {
      console.log(responseData, "헬스프로그램 목록 요청");

      //결과를 보여줄 위치
      const tbody = $(".htable tbody");
      console.log(tbody.html());

      // 현재 tbody의 내용을 비우기
      tbody.empty();

      //데이터를 테이블에 삽입
      for (let i = 0; i < responseData.length; i++) {
        const tr = $("<tr></tr>");
        const str = `<a href="program?programNo=${responseData[i].programNo}">${responseData[i].programName}</a>`;
        const link = $("<td></td>").html(str);

        //링크 클릭-세션 저장
        link.click(() => {
          sessionStorage.setItem("programNo", responseData[i].programNo);
        });

        tr.append($("<td></td>").append(link));
        tr.append($("<td></td>").text(responseData[i].programMonth));
        tr.append($("<td></td>").text(responseData[i].center));
        tr.append($("<td></td>").text(responseData[i].maxCapacity));
        tr.append($("<td></td>").text(responseData[i].programTime));
        tbody.append(tr);
      }
    },
    error: (xhr) => {
      alert("에러:" + xhr.status);
    },
  });
});
