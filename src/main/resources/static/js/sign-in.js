const form = document.getElementById("signForm");

form.addEventListener('submit', (e) => {
    e.preventDefault();  // 폼 제출을 막고, JavaScript로 처리

    let vali_text = "";
    let vali_check = false;

    // 입력값 유효성 검사
    if (form.id.value === "") {
        vali_text += "아이디를 입력하세요.\n";
        form.id.focus();
    } else if (form.pw.value === "") {
        vali_text += "비밀번호를 입력하세요.\n";
        form.pw.focus();
    } else {
        vali_check = true;
    }

    // 유효성 검사 실패 시 경고 메시지
    if (!vali_check) {
        Swal.fire({
            icon: 'info',
            title: '실패',
            text: vali_text
        });
    } else {
        // 유효성 검사 성공 시 폼을 실제로 제출
        form.submit();  // Spring Security의 폼 로그인 처리로 넘어감
    }
});

// 페이지가 로드될 때 URL 파라미터 확인 (로그인 실패시 메시지 표시)
window.onload = function () {
    // 실패 핸들러에서 가져온 location 검색
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');

    if (error) {
        Swal.fire({
            icon: 'error',
            title: '로그인 실패',
            text: "아이디 또는 비밀번호가 일치하지 않습니다."
        });
    }
};
