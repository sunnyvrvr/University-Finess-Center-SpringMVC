$(() => {
  /*세션 로그아웃, 마이페이지 버튼, 로그인 버튼 처리 */
  const loginM = sessionStorage.getItem("member");
  //const loginId = sessionStorage.getItem("loginId");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");
  // 로그아웃 버튼 참조
  //const logoutBtn = document.getElementById("logoutBtn");
  // 마이페이지 버튼 참조
  const myPageBtn = document.getElementById("myPageBtn");

  if (!loginM) {
    //세션스토리지 존재x-로그아웃, 마이페이지 버튼 숨김
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
  //  myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
  }

  //DOM트리에서 form객체찾기
  const signupObj = $("form.signup");
  console.log(signupObj.html());
  //아이디 입력란 객체 찾기
  const signupIdObj = $("form.signup input[name=loginId]");
  //아이디 중복확인 버튼 객체 찾기
  const btIdDupChk = $('form.signup input[type="button"][name="iddupchk"]');

  //패스워드 입력 버튼 객체 찾기
  const inputpwdObj = $("form.signup input[name=pwd]");
  //패스워드 입력확인 버튼 객체 찾기
  const inputpwd2Obj = $("form.signup input[name=pwd2]");

  //학번 입력 버튼 객체 찾기
  const studentIdObj = $("form.signup input[name=studentId]");
  //학번 중복확인 버튼 객체 찾기
  const btStudentIdChk = $(
    'form.signup input[type="button"][name="studentIddupchk"]'
  );

  //아이디 중복확인
  btIdDupChk.click(() => {
    const loginIdInput = $("form.signup input[name=loginId]").val();
    if (loginIdInput === "") {
      alert("아이디를 입력해주세요");
      return;
    }
    $.ajax({
      url: "/iddupchk",
      method: "post",
      data: { idcheck: loginIdInput },
      success: (responseData) => {
        console.log(responseData, "아이디중복확인");
        if (responseData === "") {
          alert("아이디중복확인 실패");
        } else {
          alert("아이디중복확인 성공");
        }
      },
      error: (xhr) => {
        alert("에러:" + xhr.status + xhr.error);
      },
    });
  });
  //비밀번호 입력객체에 값을 못넣게 하고 싶음
  //인풋객체. lost focus 이벤트
  // focus이벤트
  // focus 는 비교하는 이벤트

  inputpwd2Obj.on("blur", () => {
    //비밀번호 입력값
    const pwdInput1 = $("form.signup input[name=pwd]").val();
    const pwdInput2 = $("form.signup input[name=pwd2]").val();
    if (pwdInput1 !== pwdInput2) {
      alert("비밀번호가 일치하지 않습니다");
    }
  });

  //학번 중복확인
  btStudentIdChk.click(() => {
    const studentId = $("form.signup input[name=studentId]").val();
    if (studentId === "") {
      alert("학번을 입력해주세요");
      return;
    }
    $.ajax({
      url: "/studentiddupchk",
      method: "post",
      data: { studentidcheck: studentId },
      success: (responseData) => {
        console.log(responseData, "학번중복확인");
        if (responseData === "") {
          alert("학번중복확인 실패");
        } else {
          alert("학번중복확인 성공");
        }
      },
      error: (xhr) => {
        alert("에러:" + xhr.status + xhr.error);
      },
    });
  });

  //폼태그

  signupObj.submit((e) => {
    console.log(signupObj.serialize());
    $.ajax({
      url: "/signup",
      method: "post",
      //contentType: "application/json",
      data: signupObj.serialize(),
      success: (responseData) => {
        console.log(responseData, "회원가입확인");
        if (responseData === "success") {
          //가입 성공인경우
          alert("회원가입이 되었습니다");
          location.href = "./login"; //페이지 이동
        }
      },
      error: (xhr) => {
        alert("에러:" + xhr.status + ", 내용:" + xhr.responseText);
      },
    });
    return false;
  });
});
