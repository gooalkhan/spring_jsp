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
    sock.send('test message from client');
};

sock.onmessage = function (e) {
    alert(e.data);
};

sock.onclose = function () {
    console.log('close');
};
console.log("websocket start")