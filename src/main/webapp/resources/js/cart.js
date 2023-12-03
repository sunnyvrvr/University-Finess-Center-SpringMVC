$(() => {
  /*세션 로그아웃, 마이페이지 버튼, 로그인 버튼 처리 */
  const loginM = sessionStorage.getItem("member");
  //const loginId = sessionStorage.getItem("loginId");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");
  // 로그아웃 버튼 참조
  const logoutBtn = document.getElementById("logoutBtn");
  // 마이페이지 버튼 참조
  const myPageBtn = document.getElementById("myPageBtn");
  // 회원가입 버튼 참조
  const signupBtn = document.getElementById("signupBtn");

  if (!loginM) {
    //세션스토리지 존재x-로그아웃, 마이페이지 버튼 숨김
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
 //   myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
    alert("로그인 후에 사용해주세요");
    location.href = "./login"; //페이지 이동
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인,회원가입 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
    signupBtn.style.display = "none"; //세션스토리지 존재-회원가입버튼 숨김
  }

  let cartItems = [];
  const existingCart = $.cookie("cart");
  alert("exisingCart " + existingCart);
  if (existingCart) {
    cartItems = JSON.parse(existingCart); // 문자열을 배열로 변환
  }
  alert("cartItems " + cartItems);
  const JSONcart = JSON.stringify(cartItems);
  alert("JSONcart" + JSONcart);

  /*카트에 담긴 프로그램 번호별로 프로그램 확인하기*/
  $.ajax({
    url: "/cartdata",
    type: "get",
    data: { programNo: JSONcart },
    success: (responseData) => {
      console.log(responseData);
      // 데이터의 노출영역 찾기
      const tbody = $(".ctable tbody");

      // 현재 tbody의 내용을 비우기
      tbody.empty();

      //  const img = $(".thumbnail img");
      //const n = $("#programname");
      //const m = $("#programmonth");
      //const t = $("#programtime");
      //const p = $("#programprice");

      alert(responseData.length);
      //데이터를 영역별로 넣기
      for (let i = 0; i < responseData.length; i++) {
        //   	img.attr("src", responseData[i].programImage);
        //n.text(responseData[i].programName);
        //m.text(responseData[i].programMonth);
        //t.text(responseData[i].programTime);
        //p.text(responseData[i].programPrice);

        //이미지 태그 생성
        const img = $("<img>").attr("src", responseData[i].programImage);

        const tr = $("<tr></tr>");
        tr.append(
          $("<td></td>").append($("<div class='thumbnail'></div>").append(img))
        );
        tr.append($("<td></td>").text(responseData[i].programName));
        tr.append($("<td></td>").text(responseData[i].programMonth));
        tr.append($("<td></td>").text(responseData[i].programPrice));
        tr.append($("<td></td>").text(responseData[i].programTime));
        tbody.append(tr);
      }
    },
    error: function (xhr) {
      alert(
        "상태값 : " + xhr.status + "\tHttp 에러메시지 : " + xhr.responseText
      );
    },
  });
});
