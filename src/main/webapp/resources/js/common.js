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