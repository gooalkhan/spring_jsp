function validateMemberForm(key) {
    if (key === "join") {
        let pw = document.forms["memberjoinform"]["pw"].value;
        let pw2 = document.forms["memberjoinform"]["pw2"].value;
    } else if (key === "update") {
        let pw = document.forms["memberUpdateForm"]["pw"].value;
        let pw2 = document.forms["memberUpdateForm"]["pw2"].value;
    }

    if (pw !== pw2) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
}