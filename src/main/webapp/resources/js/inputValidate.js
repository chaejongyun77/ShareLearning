function validateInput(inputFieldId) {
    var inputField = document.getElementById(inputFieldId);

    inputField.oninput = function() {
        // 입력된 값에 영어 소문자 및 숫자만 포함되어 있는지 확인
        var validPattern = /^[a-z0-9]+$/;
        if (!validPattern.test(inputField.value)) {
            // 유효하지 않은 문자가 포함된 경우 경고
            alert("영어 소문자 및 숫자만 입력 가능합니다.");
            inputField.value = ""; // 입력 필드 초기화
        }
    };
}

function validateBlank(inputFieldId) {

    var inputField = document.getElementById(inputFieldId);
    if(inputField.value === "") {
        alert("아이디를 입력해주세요.");
        return false; // 이후 로직 실행을 막기 위해 함수 실행을 여기서 중단합니다.
    }

}