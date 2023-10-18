function validateMemberForm(key) {
    if (key === "join") {
        var pw = document.forms["memberjoinform"]["pw"].value;
        var pw2 = document.forms["memberjoinform"]["pw2"].value;
    } else if (key === "update") {
        var pw = document.forms["memberUpdateForm"]["pw"].value;
        var pw2 = document.forms["memberUpdateForm"]["pw2"].value;
    }

    if (pw !== pw2) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
}

function comment_form_check() {
    var comment = document.forms["comment_form"]["content"].value;

    if (comment === "") {
        alert("댓글을 입력해주세요.");
        return false;
    }
}

function board_form_check() {
    var subject = document.forms["board_form"]["subject"].value;
    var content = document.forms["board_form"]["content"].value;

    if (subject === "") {
        alert("제목을 입력해주세요.");
        return false;
    } else if (content === "") {
        alert("내용을 입력해주세요.");
        return false;
    }
}

function isSameMessage(message) {
    let toastContainer = document.getElementById('toast-container');
    let toastList = toastContainer.querySelectorAll('.toast');

    for (let i = 0; i < toastList.length; i++) {
        let toastText = toastList[i].querySelector('#toast-body').innerText;

        if (toastText === message) {
            return true;
        }
    }

    return false;
}


let sock = new SockJS('http://localhost:8080/message');

sock.onopen = function () {
    console.log('open');
    sock.send('test message from client');
    console.log("websocket start")
};

sock.onmessage = function (e) {
    console.log('message', e.data);

    let data = JSON.parse(e.data);
    let datetime = Math.floor((Date.now() - Date.parse(data.messageDate)) / 60000);

    let toastContainer = document.getElementById('toast-container');
    let myToast = document.getElementById('myToast').cloneNode(true);

    let toastText = myToast.querySelector('#toast-body')
    let toastTitle = myToast.querySelector('#toast-title')

    toastTitle.innerText = datetime + "분 전";
    toastText.innerText = data.message;

    if (!isSameMessage(data.message)) {
        toastContainer.appendChild(myToast);
        let myToastInstance = new bootstrap.Toast(myToast);
        myToastInstance.show();
    }

};

sock.onclose = function () {
    console.log('websocket close');
};