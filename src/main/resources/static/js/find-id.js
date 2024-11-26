const form = document.getElementById("signForm");

form.addEventListener('submit', (e) => {
    e.preventDefault();

    // 유효성 검사
    let vali_check = false;
    let vali_text="";

    if(form.userName.value == ""){
        vali_text += "이름을 입력하세요.";
        form.id.focus();
    }else if(form.userEmail.value == ""){
        vali_text += "이메일을 입력하세요.";
        form.pwd.focus();
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
        fetch('/find-id',{
            method : 'POST',
            headers : {
                'Content-Type': 'application/json', //JSON 형식으로 전달
            },
            body: JSON.stringify(payload) // JSON 형식으로 데이터 전송
        })
        .then(response => response.json())
        .then(data => {
            if(data.res_code === '200'){
            document.getElementById("userId").textContent = data.userId;
                document.getElementById('checkUserId').style.display="flex";

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