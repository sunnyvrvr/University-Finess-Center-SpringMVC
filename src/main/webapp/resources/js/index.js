$(() => {
  /*세션 로그아웃, 마이페이지 버튼, 로그인 버튼 처리 */
  const loginM = sessionStorage.getItem("member");
  //const loginId = sessionStorage.getItem("loginId");
  // 로그인 버튼 참조
  const loginBtn = document.getElementById("loginBtn");
  // 로그아웃 버튼 참조
  const logoutBtn = document.getElementById("logoutBtn");
  // 마이페이지 버튼 참조
//  const myPageBtn = document.getElementById("myPageBtn");
  // 회원가입 버튼 참조
  const signupBtn = document.getElementById("signupBtn");

  if (!loginM) {
    //세션스토리지 존재x-로그아웃, 마이페이지 버튼 숨김
    logoutBtn.style.display = "none"; //세션스토리지 존재x-로그아웃버튼 숨김
 //   myPageBtn.style.display = "none"; //세션스토리지 존재x-마이페이지버튼 숨김
  } else {
    //세션스토리지 존재-로그아웃버튼 노출, 로그인,회원가입 버튼 숨김
    logoutBtn.style.display = "block"; //세션스토리지 존재-로그아웃버튼 노출
    loginBtn.style.display = "none"; //세션스토리지 존재-로그인버튼 숨김
    signupBtn.style.display = "none"; //세션스토리지 존재-회원가입버튼 숨김
  }
  
  
$(document).ready(function() {
  /* FullCalendar 초기화 */
  var calendarEl = $('#calendar')[0];
  var calendar = new FullCalendar.Calendar(calendarEl, {
    header: {
      left: 'prev,next today',
      center: 'title',
      right: 'month,agendaWeek,agendaDay'
    },
    defaultDate: '2023-12-23',
    navLinks: true,
    editable: false,
    eventLimit: true,
    events: [
      {
        title: '성탄절 연휴 휴무',
        start: '2023-12-23',
        end: '2023-12-26',
        color: '#FF5733'
      }
      // 추가 이벤트가 있다면 여기에 계속 추가할 수 있습니다.
    ]
  });

  /* FullCalendar 렌더링 */
  calendar.render();
});

  
});
