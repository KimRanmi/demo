const form = document.getElementById("signForm");

form.addEventListener('submit', (e) => {
    e.preventDefault();

    // 유효성 검사
    let vali_check = false;
    let vali_text="";

    if(form.id.value == ""){
        vali_text += "아이디를 입력하세요.";
        form.id.focus();
    }else if(form.pwd.value == ""){
        vali_text += "비밀번호를 입력하세요.";
        form.pwd.focus();
    }else if(form.pw_check.value ==""){
        vali_text += "비밀번호를 입력하세요.";
        form.pw_check.focus();
    }else if(form.name.value == ""){
        vali_text += "이름을 입력하세요.";
    }else if(form.num.value == ""){
        vali_text += "핸드폰 번호를 입력하세요.";
    }else if(form.email.value == ""){
        vali_text += "이메일을 입력하세요.";
    }else if(form.pw_check.value != form.pwd.value){
        vali_text += "비밀번호가 일치하지 않습니다.";
    }else{
        vali_check = true;
    }

    if(vali_check === false){
        Swal.fire({
            icon : 'info',
            title : '실패',
            text : vali_text
        });
    }else{
    // FormData를 JSON 객체로 변환
         const formData = new FormData(form);
         const payload = {};
         formData.forEach((value, key) => {
              payload[key] = value;
            });
        console.log(payload);
        fetch('/sign-up',{
            method : 'POST',
            headers : {
                'Content-Type': 'application/json', //JSON 형식으로 전달
            },
            body: JSON.stringify(payload) // JSON 형식으로 데이터 전송
        })
        .then(response => response.json())
        .then(data => {
            if(data.res_code === '200'){
                Swal.fire({
                    icon : 'success',
                    title : "성공",
                    text : data.res_msg
                }).then(()=>{
                    location.href = "/login";
                })
            }else{
                Swal.fire({
                    icon: 'error',
                    title : "실패",
                    text : data.res_msg
                });
            }
        })
    }
})