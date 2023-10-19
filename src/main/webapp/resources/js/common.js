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

let sock = new SockJS('http://localhost:8080/message');

sock.onopen = function () {
    console.log('open');
    console.log("websocket start")
};

sock.onmessage = function (e) {
    console.log('message', e.data);

    var data = JSON.parse(e.data);
    var datetime = Math.floor((Date.now() - Date.parse(data.messageDate)) / 60000);

    var toastContainer = document.getElementById('toast-container');
    var myToast = document.getElementById('myToast').cloneNode(true);
    myToast.id = data.id;

    var toastText = myToast.querySelector('#toast-body')
    var toastTitle = myToast.querySelector('#toast-title')

    myToast.addEventListener('shown.bs.toast', function () {
        if (sock.readyState === 1) {
            sock.send(data.id);
        }
    })

    toastTitle.innerText = datetime + "분 전";
    toastText.innerText = data.message;

    toastContainer.appendChild(myToast);
    var myToastInstance = new bootstrap.Toast(myToast);
    myToastInstance.show();

};

sock.onclose = function () {
    console.log('websocket close');
};

function getKeywordAnalysis(querystring) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/keyword?" + querystring, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = xhr.responseText;
            document.getElementById("analysis").innerHTML = response;
        }
    };
    xhr.send();
}