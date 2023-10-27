const inputElement = document.getElementById('uploadfile');
const messageElement = document.getElementById('message');
const maxFiles = 512; // 올릴 수 있는 이미지는 512장으로 제한
const allowedExtensions = ["jpg", "jpeg", "png", "gif", "bmp"]; // 제한 하고자 하는 확장자

inputElement.addEventListener('change', function () {
  	const files = inputElement.files;
  	let invalidFiles = [];
		if (files.length > maxFiles) {
    		messageElement.textContent = `${maxFiles} 개의 파일 까지만 올릴 수 있습니다.`;
    		inputElement.value = ''; // 파일 선택 초기화
    		return;
  		}
  		
	// 확장자 필터
	/*
	필독: gif를 예시로 들었을 때,
	gif 더라도 비 정상적인 gif면 accept='.jpg,.jpeg,.bmp,.png,.gif' 여기 부분에서 자동으로 걸러짐.
	따라서 비정상적인 gif 1장, 정상적인 gif 3장을 첨부해서 등록했을 때,
	정상적인 3장의 파일만 DB에 등록되고 지정된 폴더에 만들어짐.
	
	이미지 월드컵 같은 경우에는 부전승을 구현하면 신경 안써도 되는 문제이긴 함.
	*/
  	for (let i = 0; i < files.length; i++) {
   	 	const fileName = files[i].name;
    	const fileExtension = fileName.split('.').pop().toLowerCase();
		// 만약, allowedExtensions에 들어있는 확장자 이름 중, 하나라도 해당되지 않는다면 invalidFiles에 값을 넣음
		if (allowedExtensions.indexOf(fileExtension) === -1) {
      		invalidFiles.push(fileName);
   		}
  	}
	
	// 지원하지 않는 확장자의 파일이 있다면
  	if (invalidFiles.length > 0) {
    	messageElement.textContent = `지원하지 않는 확장자의 파일(들) 감지: ${invalidFiles.join(', ')}`;
    	inputElement.value = ''; // 파일 선택 초기화
  	} else {
    	messageElement.textContent = '';
  	}
});