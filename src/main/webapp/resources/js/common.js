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

function worldcup_form_check() {
    var subject = document.forms["worldcup_form"]["subject"].value;
    var content = document.forms["worldcup_form"]["content"].value;

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

function getAnalysis(bookid, productid) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/analysis?bookid=" +bookid + "&productid=" + productid, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var parser = new DOMParser();
            var newNode = parser.parseFromString(xhr.responseText, 'text/html').getElementById(productid)
            var container = document.getElementById("analysis-container");
            var oldNode = document.getElementById(productid);
            container.replaceChild(newNode, oldNode);
            // document.getElementById("analysis-"+productid).outerHTML = xhr.responseText;
        }
    };
    xhr.send();
}

function purchaseAnalysis(formuid) {

    var xhr = new XMLHttpRequest();
    var formData = document.forms[formuid];
    var body = new FormData(formData);

    xhr.open("POST", "http://localhost:8080/analysis", true);
    xhr.withCredentials = true;
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = xhr.responseText;
            console.log(response)
            var json = JSON.parse(response)
            if (json.status === "success") {

                list = document.getElementsByClassName("current-point");
                for (var i = 0; i < list.length; i++) {
                    list[i].innerText = json.current_point;
                }

                getAnalysis(formData["bookid"].value, formData["productid"].value);

                document.getElementById(formuid).remove();
            }
        }
    };
    xhr.send(body);
    return false;
}