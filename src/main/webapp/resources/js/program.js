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
  //  myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인,회원가입 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
    signupBtn.style.display = "none"; //세션스토리지 존재-회원가입버튼 숨김
  }

  const programNo = sessionStorage.getItem("programNo");

  const getCookies = () => {
    const cookieString = document.cookie;
    const cookieArray = cookieString.split(";").map((cookie) => {
      const [name, value] = cookie.trim().split("=");
      return {
        name: decodeURIComponent(name),
        value: decodeURIComponent(value),
      };
    });
    return cookieArray;
  };

  const cookies = getCookies();
  console.log("cookies" + cookies);
  const existingCart = $.cookie("cart");
  console.log("현재 cart 쿠키" + existingCart);

  //쿠키에 장바구니 추가
  const cartBtn = $("#addcart");
  cartBtn.click(() => {
    let cartItems = [];
    //let uniquecartItems = [...new Set(cartItems)];
    const existingCart = $.cookie("cart");

    if (existingCart) {
      cartItems = JSON.parse(existingCart); // 문자열을 배열로 변환
    }

    // cartItems.push(programNo); // 배열에 새 항목 추가
    cartItems.push(programNo);

    $.cookie("cart", JSON.stringify(cartItems), { expires: 3, path: "/" }); // 배열을 문자열로 변환하여 쿠키에 저장
    location.href = "./cart";
  });

  /*프로그램 번호별로 프로그램 확인하기*/
  $.ajax({
    url: "/programdata",
    type: "get",
    data: { programNo: programNo },
    success: (responseData) => {
      console.log(responseData);
      // 데이터의 노출영역 찾기
      const img = $(".thumbnail img");
      const name = $("#programname");
      const center = $("#programcenter");
      const max = $("#programmaxcapacity");
      const time = $("#programtime");
      const detail = $("#programdetail");
      const price = $("#programprice");

      img.attr("src", responseData.programImage);
      name.text(responseData.programName);
      price.text(responseData.programPrice);

      center.text(responseData.center);
      max.text(responseData.maxCapacity);
      time.text(responseData.programTime);
      detail.text(responseData.programDetail);
    },
    error: function (xhr) {
      alert("에러:" + xhr.status);
    },
  });
});
